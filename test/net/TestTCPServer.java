package net;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTCPServer {

    public static final long BIND_TIME      = 60; // TCP bind (ms)
    public static final long HANDSHAKE_TIME = 60; // TCP handshake (ms)
    public static final long TRANSFER_TIME  =  5; // TCP data transfer (ms)

    public static final String host = "127.0.0.1";
    public static final int    port = 5000;
    public static final String expected[] = {
        "{}",
        "{\"foo\": 1}",
        "{\"foo\": 1, \"bar\": 2}",
        "[]",
        "[1]",
        "[1,2]"
    };

    // Test initialize() method.
    @Test
    public void testInitialize() {
        try {
            FakeServerModel model = new FakeServerModel();
            TCPServer server = new TCPServer(model);
            server.initialize(port);
            Thread.sleep(BIND_TIME);
            Socket socket = new Socket(host, port);
            socket.close();
            server.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test getIPTable() method.
    @Test
    public void testGetIPTable() {
        try {
            FakeServerModel model = new FakeServerModel();
            TCPServer server = new TCPServer(model);
            server.initialize(port);
            Thread.sleep(BIND_TIME);
            ArrayList<Socket> socks = new ArrayList<Socket>();
            for (int i = 0; i < 20; i++) {
                Socket socket = new Socket(host, port);
                Thread.sleep(HANDSHAKE_TIME);
                socks.add(socket);
                Vector<InetAddress> iptable = server.getIPTable();
                for (InetAddress addr : iptable) {
                    assertEquals(addr.getHostAddress(), host);
                }
                assertEquals(iptable.size(), socks.size());
            }
            Iterator<Socket> itor = socks.iterator();
            while (itor.hasNext()) {
                Socket socket = itor.next();
                socket.close();
                itor.remove();
                Thread.sleep(TRANSFER_TIME);
                Vector<InetAddress> iptable = server.getIPTable();
                assertEquals(iptable.size(), socks.size());
            }
            server.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test whether Server can receive data.
    @Test
    public void testReceivingData() {
        try {
            final Vector<String> actual = new Vector<String>();
            FakeServerModel model = new FakeServerModel() {
                public void set(byte body[]) {
                    actual.add(new String(body, StandardCharsets.UTF_8));
                }
            };
            TCPServer server = new TCPServer(model);
            server.initialize(port);
            Thread.sleep(BIND_TIME);
            Socket socket = new Socket(host, port);
            DataOutputStream out =
                new DataOutputStream(socket.getOutputStream());
            for (String data : expected) {
                out.writeInt(data.length());
                out.write(data.getBytes(StandardCharsets.UTF_8));
            }
            Thread.sleep(TRANSFER_TIME);
            out.close();
            socket.close();
            server.close();
            for (int i = 0; i < expected.length; i++) {
                assertTrue(actual.get(i).equals(expected[i]));
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test whether Server can graceful shutdown when
    // the socket is connected.
    @Test
    public void testCloseServerBeforeSocketClosed() {
        try {
            FakeServerModel model = new FakeServerModel();
            TCPServer server = new TCPServer(model);
            server.initialize(port);
            Thread.sleep(BIND_TIME);
            Socket socket = new Socket(host, port);
            server.close();
            socket.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test Server a the assertion that
    // Server should be initialized before being closed.
    @Test(expected = AssertionError.class)
    public void testCloseServerBeforeServerInitialized() {
        try {
            FakeServerModel model = new FakeServerModel();
            TCPServer server = new TCPServer(model);
            server.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test Server a the assertion that
    // Server should be initialized before being closed.
    @Test(expected = AssertionError.class)
    public void testCloseServerTwice() {
        try {
            FakeServerModel model = new FakeServerModel();
            TCPServer server = new TCPServer(model);
            server.initialize(port);
            Thread.sleep(BIND_TIME);
            server.close();
            server.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
