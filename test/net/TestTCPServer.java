package net;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import static suite.TestSuite.*;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTCPServer {

    // Test initTCPServer() method.
    @Test
    public void testInitTCPServer() {
        try {
            FakeServerModel model = new FakeServerModel();
            TCPServer server = new TCPServer(model);
            server.initTCPServer();
            Thread.sleep(SETUP_TIME);
            Socket socket = new Socket("127.0.0.1", 5000);
            socket.close();
            server.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test getClientIPTable() method.
    @Test
    public void testGetClientIPTable() {
        try {
            FakeServerModel model = new FakeServerModel();
            TCPServer server = new TCPServer(model);
            server.initTCPServer();
            Thread.sleep(SETUP_TIME);
            ArrayList<Socket> socks = new ArrayList<Socket>();
            for (int i = 0; i < 20; i++) {
                Socket socket = new Socket("127.0.0.1", 5000);
                Thread.sleep(ACCEPT_TIME);
                socks.add(socket);
                Vector<InetAddress> iptable = server.getClientIPTable();
                for (InetAddress addr : iptable) {
                    assertEquals(addr.getHostAddress(), "127.0.0.1");
                }
                assertEquals(iptable.size(), socks.size());
            }
            Iterator<Socket> itor = socks.iterator();
            while (itor.hasNext()) {
                Socket socket = itor.next();
                socket.close();
                itor.remove();
                Thread.sleep(TRANSFER_TIME);
                Vector<InetAddress> iptable = server.getClientIPTable();
                assertEquals(iptable.size(), socks.size());
            }
            server.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // Test whether Server can handle movecode.
    @Test
    public void testHandlingMovecode() {
        try {
            String expected[] = {
                "{}",
                "{\"foo\": 1}",
                "{\"foo\": 1, \"bar\": 2}",
                "[]",
                "[1]",
                "[1,2]"
            };
            final Vector<String> actual = new Vector<String>();
            FakeServerModel model = new FakeServerModel() {
                public void set(byte body[]) {
                    actual.add(new String(body, StandardCharsets.UTF_8));
                }
            };
            TCPServer server = new TCPServer(model);
            server.initTCPServer();
            Thread.sleep(SETUP_TIME);
            Socket socket = new Socket("127.0.0.1", 5000);
            DataOutputStream out =
                new DataOutputStream(socket.getOutputStream());
            for (String body : expected) {
                out.writeInt(body.length());
                out.write(body.getBytes(StandardCharsets.UTF_8));
            }
            Thread.sleep(TRANSFER_TIME);
            for (int i = 0; i < expected.length; i++) {
                assertTrue(actual.get(i).equals(expected[i]));
            }
            out.close();
            socket.close();
            server.close();
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
            server.initTCPServer();
            Thread.sleep(SETUP_TIME);
            Socket socket = new Socket("127.0.0.1", 5000);
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
            server.initTCPServer();
            Thread.sleep(SETUP_TIME);
            server.close();
            server.close();
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
