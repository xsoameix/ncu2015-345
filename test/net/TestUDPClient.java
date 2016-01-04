package net;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;

import model.ServerModel;

public class TestUDPClient {

	private String host         = "127.0.0.1";
	private int    port         = 5000;
	private long   bindTime     = 200;

	//test initialize() method
	@Test
	public void testInitialize() throws Exception {
		ServerModel model = new ServerModel();
		TCPServer server = new TCPServer(model);
		UDPClient client = new UDPClient(server);
		client.initialize(port);
		client.close();
	}

	//test send() method
	@Test
	public void testSend() throws Exception {
		String expected = "hello world";
		final ArrayList<String> actual = new ArrayList<String>();

		//create UDP server
		Thread thread = new Thread() {
			public void run() {
		        final int size = 8192;
		        byte buffer[] = new byte[size];
		        DatagramPacket packet =
		        		new DatagramPacket(buffer, buffer.length);
			    try {
				    DatagramSocket socket = new DatagramSocket(port);
					socket.receive(packet);
					String body = new String(buffer, 0, packet.getLength(),
							StandardCharsets.UTF_8);
					actual.add(body);
					socket.close();
				} catch (IOException e) {
				    e.printStackTrace();
				}           
		    }
		};
		thread.start();
		Thread.sleep(bindTime);

		//create UDP client
		ServerModel model = new ServerModel();
		TCPServer server = new TCPServer(model) {
			public Vector<InetAddress> getIPTable() {
				Vector<InetAddress> iptable =
						new Vector<InetAddress>();
				try {
					iptable.add(InetAddress.getByName(host));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				return iptable;
			}
		};
		UDPClient client = new UDPClient(server);
		client.initialize(port);
		client.send(expected);
		client.close();
		thread.join();
		assertEquals(1, actual.size());
		assertEquals(expected, actual.get(0));
	}
}
