package net;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import static net.TestTCPServer.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestWorker {

    // Test whether Worker can receive data.
    @Test
    public void testHandlingMovecode() {
        try {
            AtomicInteger counter = new AtomicInteger(1);
            ServerSocketChannel server = ServerSocketChannel.open();
            InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 5000);
            server.bind(addr);
            Thread clientThread = new Thread() {
                public void run() {
                    try {
                        Socket socket = new Socket("127.0.0.1", 5000);
                        DataOutputStream out =
                            new DataOutputStream(socket.getOutputStream());
                        for (String data : expected) {
                            out.writeInt(data.length());
                            out.write(data.getBytes(StandardCharsets.UTF_8));
                        }
                        out.close();
                        socket.close();
                    } catch (Exception e) {
                    }
                }
            };
            clientThread.start();
            SocketChannel socket = server.accept();
            Vector<InetAddress> IPTable = new Vector<InetAddress>();
            Pipe ctrlPipe = Pipe.open();
            SinkChannel ctrlIn = ctrlPipe.sink();
            SourceChannel ctrlOut = ctrlPipe.source();
            ctrlOut.configureBlocking(false);
            final Vector<String> actual = new Vector<String>();
            FakeServerModel model = new FakeServerModel() {
                public void set(byte body[]) {
                    actual.add(new String(body, StandardCharsets.UTF_8));
                }
            };
            Worker worker = new Worker(socket,
                    IPTable, ctrlOut, counter, model);
            Thread workerThread = new Thread(worker);
            workerThread.start();
            Thread.sleep(TRANSFER_TIME);
            ByteBuffer buf = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
            buf.putInt(TCPServer.PIPE_EXIT);
            buf.flip();
            ctrlIn.write(buf);
            workerThread.join();
            for (int i = 0; i < expected.length; i++) {
                assertEquals(actual.get(i), expected[i]);
            }
            ctrlIn.close();
            ctrlOut.close();
            clientThread.join();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
}
