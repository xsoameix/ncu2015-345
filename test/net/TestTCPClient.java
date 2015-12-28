package net;

import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

import static net.TestTCPServer.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTCPClient {

    // Test initialize() method.
    @Test
    public void testInitialize() {
        try {
            final AtomicBoolean called = new AtomicBoolean(false);
            Thread thread = new Thread() {
                public void run() {
                    try {
                        ServerSocket server = new ServerSocket(port);
                        Socket socket = server.accept();
                        socket.close();
                        server.close();
                        called.set(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            TCPClient client = new TCPClient();
            InetAddress addr = InetAddress.getByName(host);
            Thread.sleep(BIND_TIME);
            boolean successful = client.initialize(addr, port);
            client.close();
            thread.join();
            assertTrue(successful);
            assertTrue(called.get());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test send() method.
    @Test
    public void testSend() {
        try {
            final Vector<String> actual = new Vector<String>();
            Thread thread = new Thread() {
                public void run() {
                    try {
                        ServerSocket server = new ServerSocket(port);
                        Socket socket = server.accept();
                        DataInputStream in =
                            new DataInputStream(socket.getInputStream());
                        for (int i = 0; i < expected.length; i++) {
                            int size = in.readInt();
                            byte body[] = new byte[size];
                            in.readFully(body);
                            String data = new String(body, StandardCharsets.UTF_8);
                            actual.add(data);
                        }
                        in.close();
                        socket.close();
                        server.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            TCPClient client = new TCPClient();
            InetAddress addr = InetAddress.getByName(host);
            Thread.sleep(BIND_TIME);
            client.initialize(addr, port);
            for (String data : expected) {
                client.send(data.getBytes(StandardCharsets.UTF_8));
            }
            client.close();
            thread.join();
            assertEquals(actual.size(), expected.length);
            for (int i = 0; i < expected.length; i++) {
                assertTrue(actual.get(i).equals(expected[i]));
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test initialize() method.
    // Server should return false if argument `serverip` is not correct.
    @Test(expected = UnknownHostException.class)
    public void testConnectServerArgumentServerip()
    throws UnknownHostException {
        TCPClient client = new TCPClient();
        InetAddress addr = InetAddress.getByName("127.0.0.wrong");
        boolean successful = client.initialize(addr, port);
        assertFalse(successful);
        client.close();
    }

    // Test whether Client has a assertion that
    // Client shoule close the previous connection before connecting to Server.
    @Test(expected = AssertionError.class)
    public void testConnectServerTwice() {
        try {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        ServerSocket server = new ServerSocket(port);
                        Socket socket = server.accept();
                        socket.close();
                        server.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            TCPClient client = new TCPClient();
            InetAddress addr = InetAddress.getByName(host);
            Thread.sleep(BIND_TIME);
            client.initialize(addr, port);
            try {
                client.initialize(addr, port);
            } finally {
                client.close();
                thread.join();
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test whether Client has a assertion that
    // Client shoule connect to Server before sending data.
    @Test(expected = AssertionError.class)
    public void testInputMoveBeforeConnectServer() {
        TCPClient client = new TCPClient();
        client.send("{}".getBytes(StandardCharsets.UTF_8));
    }

    // Test whether Client has a assertion that
    // Client shoule connect to Server before being closed.
    @Test(expected = AssertionError.class)
    public void testCloseTwice() {
        try {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        ServerSocket server = new ServerSocket(port);
                        Socket socket = server.accept();
                        socket.close();
                        server.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            TCPClient client = new TCPClient();
            InetAddress addr = InetAddress.getByName(host);
            Thread.sleep(BIND_TIME);
            client.initialize(addr, port);
            client.close();
            try {
                client.close();
            } finally {
                thread.join();
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test whether Client has a assertion that
    // Client shoule connect to Server before being closed.
    @Test(expected = AssertionError.class)
    public void testCloseBeforeConnectServer() {
        TCPClient client = new TCPClient();
        client.close();
    }
}
