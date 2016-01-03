package net;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class UDPClient {

	private static final int STATE_INITIAL   = 0;
    private static final int STATE_CONNECTED = 1;

	private int            port;
	private TCPServer      server;
	private DatagramSocket socket;
    private int            state = STATE_INITIAL;

    public UDPClient(TCPServer server) {
    	this.server = server;
    }

    public void initialize(int port) {
    	try {
        	assert state == STATE_INITIAL : "client should be initial";
    		assert port > 1024 && port < 65536 : "port over range";
    		this.state = STATE_CONNECTED;
    		this.port = port;
        	this.socket = new DatagramSocket();
    	} catch (SocketException e) {
    		e.printStackTrace();
    	}
    }

    public void send(String body) {
    	try {
    		assert state == STATE_CONNECTED : "client should be connected";
    		Vector<InetAddress> iptable = server.getIPTable();
    		synchronized(iptable) {
    			for (InetAddress addr : iptable) {
    				byte[] data = body.getBytes(StandardCharsets.UTF_8);
    				DatagramPacket sendPacket =
    						new DatagramPacket(data, data.length, addr, port);
    				socket.send(sendPacket);
    			}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public void close(){
    	assert state == STATE_CONNECTED : "client should be connected";
    	socket.close();
    	state = STATE_INITIAL;
    }
}