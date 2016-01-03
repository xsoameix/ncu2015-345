package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import model.ServerModel;
import model.session.Session;

public class TCPServer implements Runnable {

    public static final int PIPE_EXIT = 0;
    public static final int PIPE_SYNC = 1;

    private static final int STATE_INITIAL = 0;
    private static final int STATE_RUNNING = 1;

    private ServerModel         model;
    private int                 port;
    private Vector<InetAddress> IPTable;
    private Vector<Thread>      threads;
    private SinkChannel         ctrlIn;
    private SourceChannel       ctrlOut;
    private SinkChannel         syncIn;
    private SourceChannel       syncOut;
    private ServerSocketChannel server;
    private AtomicInteger       state;

    public TCPServer(ServerModel model) {
        this.model = model;
        this.state = new AtomicInteger(STATE_INITIAL);
    }

    public void initialize(int port) {
        this.port = port;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            assert state.get() == STATE_INITIAL : "server should be initial";
            AtomicInteger counter = new AtomicInteger(1);
            IPTable = new Vector<InetAddress>();
            threads = new Vector<Thread>();
            threads.add(Thread.currentThread());
            Selector selector = Selector.open();
            Pipe ctrlPipe = Pipe.open();
            ctrlIn = ctrlPipe.sink();
            ctrlOut = ctrlPipe.source();
            ctrlOut.configureBlocking(false);
            ctrlOut.register(selector, SelectionKey.OP_READ);
            Pipe syncPipe = Pipe.open();
            syncIn = syncPipe.sink();
            syncOut = syncPipe.source();
            syncOut.configureBlocking(false);
            server = ServerSocketChannel.open();
            server.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            server.configureBlocking(false);
            InetSocketAddress addr = new InetSocketAddress(port);
            server.bind(addr);
            server.register(selector, SelectionKey.OP_ACCEPT);
            state.set(STATE_RUNNING);
            while (true) {
                int channels = selector.select();
                if (channels == 0) continue;
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        SocketChannel client = server.accept();
                        Session session = new Session(model);
                        Worker worker = new Worker(client,
                                IPTable, ctrlOut, counter, session);
                        Thread thread = new Thread(worker);
                        thread.start();
                        threads.add(thread);
                        counter.getAndIncrement();
                    } else if (key.isReadable()) {
                        server.close();
                        selector.close();
                        ByteBuffer buf =
                            ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
                        buf.putInt(PIPE_SYNC);
                        buf.flip();
                        syncIn.write(buf);
                        return;
                    }
                }
                selector.selectedKeys().clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
            state.set(STATE_INITIAL);
        }
    }

    public void close() {
        try {
            assert state.get() == STATE_RUNNING : "server should be running";
            state.set(STATE_INITIAL);
            ByteBuffer buf = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
            buf.putInt(PIPE_EXIT);
            buf.flip();
            ctrlIn.write(buf);
            Selector selector = Selector.open();
            syncOut.register(selector, SelectionKey.OP_READ);
            for (boolean loop = true; loop; ) {
                int channels = selector.select();
                if (channels == 0) continue;
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isReadable()) {
                        synchronized(threads) {
                            Iterator<Thread> itor = threads.iterator();
                            while (itor.hasNext()) {
                                Thread thread = itor.next();
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        loop = false;
                    }
                }
                selector.selectedKeys().clear();
            }
            buf.flip();
            buf.clear();
            syncOut.read(buf);
            syncIn.close();
            syncOut.close();
            buf.clear();
            ctrlOut.read(buf);
            ctrlIn.close();
            ctrlOut.close();
            threads.clear();
            IPTable.clear();
        } catch (Exception e) {
            e.printStackTrace();
            state.set(STATE_RUNNING);
        }
    }

    public Vector<InetAddress> getIPTable() {
        return IPTable;
    }
}
