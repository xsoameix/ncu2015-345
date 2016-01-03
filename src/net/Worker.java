package net;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import model.session.Session;

public class Worker implements Runnable {

    private static final int READ_SIZE = 0;
    private static final int READ_BODY = 1;

    private SocketChannel       socket;
    private Vector<InetAddress> IPTable;
    private SourceChannel       ctrlOut;
    private AtomicInteger       counter;
    private Session             session;

    public Worker(
            SocketChannel       socket,
            Vector<InetAddress> IPTable,
            SourceChannel       ctrlOut,
            AtomicInteger       counter,
            Session             session) {
        this.socket  = socket;
        this.IPTable = IPTable;
        this.ctrlOut = ctrlOut;
        this.counter = counter;
        this.session = session;
    }

    public void run() {
        try {
            IPTable.add(socket.socket().getInetAddress());
            Selector selector = Selector.open();
            ctrlOut.register(selector, SelectionKey.OP_READ);
            socket.configureBlocking(false);
            socket.register(selector, SelectionKey.OP_READ);
            ByteBuffer sizebuf = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
            ByteBuffer bodybuf = null;
            int state = READ_SIZE;
            while (true) {
                int channels = selector.select();
                if (channels == 0) continue;
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isReadable()) {
                        if (key.channel().equals(socket)) {
                            if (state == READ_SIZE) {
                                int read = socket.read(sizebuf);
                                if (read == -1) return;
                                if (sizebuf.hasRemaining()) continue;
                                sizebuf.flip();
                                int size = sizebuf.getInt();
                                bodybuf = ByteBuffer.allocate(size);
                                sizebuf.clear();
                                state = READ_BODY;
                            } else if (state == READ_BODY) {
                                int read = socket.read(bodybuf);
                                if (read == -1) return;
                                if (bodybuf.hasRemaining()) continue;
                                bodybuf.flip();
                                byte body[] = bodybuf.array();
                                session.onData(body);
                                bodybuf.clear();
                                state = READ_SIZE;
                            }
                        } else if (key.channel().equals(ctrlOut)) {
                            socket.close();
                            selector.close();
                            return;
                        }
                    }
                }
                selector.selectedKeys().clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            counter.getAndDecrement();
            IPTable.remove(socket.socket().getInetAddress());
        }
    }
}
