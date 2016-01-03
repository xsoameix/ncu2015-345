package net;

import java.io.IOException;
import java.net.*;
import java.util.Vector;


public class UDPClient {
	private static final int STATE_INITIAL = 0;
    private static final int STATE_CONNECTED = 1;
    private int state = STATE_INITIAL;
    public InetAddress getIP;                               //test ¥Î	
	InetAddress IPTableTemp[] = new InetAddress[6];
	Vector<InetAddress> IPData = new Vector<InetAddress>();
	DatagramSocket socket;
	int setPort;
	TCPServer tcpServer;
    public UDPClient(TCPServer tcpServer){
    	this.tcpServer=tcpServer;
    }
    
    public void initialize(int port){
    	assert port>1024 && port<65536:"port over range";
    	IPData = tcpServer.getIPTable();
    	getIP = IPData.get(0);
    	setPort = port;
    	state = STATE_CONNECTED;
    }
    
    public void send(String packet) throws IOException, InterruptedException{
    	assert state == STATE_CONNECTED : "client should be connected";
    	int count = 0;
    	final int SIZE = 8192;
    	byte[] bytePacket = new byte[SIZE];    	
    	bytePacket = packet.getBytes();
    	while(true){
    		IPTableTemp[count++] = IPData.get(0);
    		DatagramPacket sendPacket = new DatagramPacket(bytePacket, bytePacket.length, IPData.get(0), setPort);
    		socket = new DatagramSocket();
    		socket.send(sendPacket);  		
    		IPData.remove(0);
    		Thread.sleep(100);    		
    		if(IPData.size()==0){
    			for(int i=0;i<count;i++){
    				IPData.add(IPTableTemp[i]);
    			} 				
    			break;
    		}
    	}
    }
    
    public void close(){
    	assert state == STATE_CONNECTED : "client should be connected";
    	socket.close();
    	state = STATE_INITIAL;
    } 
}
