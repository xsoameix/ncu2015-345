package net;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Test;

public class TestUDPClient implements Runnable {
	public static final long BIND_TIME      = 100;
	int port = 5000;
    String packet = "hello world";
    TCPServer TCPserver = new TCPServer();
	
	//test initialize() method
	@Test
	public void testInitialize() throws InterruptedException {
		try {
			UDPClient client=new UDPClient(TCPserver);
			client.initialize(port);
			assertEquals(InetAddress.getByName("127.0.0.1"),client.getIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	//test send() method
	@Test
	public void testSend() throws Exception {
		//create UDP server
		TestUDPClient server= new TestUDPClient();
		Thread thread = new Thread(server);
		thread.start();		
		Thread.sleep(BIND_TIME);
		
		//create UDP client
		UDPClient client=new UDPClient(TCPserver);
		client.initialize(port);
		Thread.sleep(BIND_TIME);
		client.send(packet);
		Thread.sleep(BIND_TIME);
		
		//close work
		thread.join();
		client.close();
	}
	
	public void run() {
        final int SIZE = 8192;
        byte buffer[] = new byte[SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	    DatagramSocket socket;		
	    try {
		    socket = new DatagramSocket(port);
			socket.receive(packet);
			String msg = new String(buffer, 0, packet.getLength());
			assertEquals(this.packet,msg);
			socket.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}           
    }
}
