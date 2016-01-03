package net;

import static org.junit.Assert.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;
import org.junit.Test;

import model.ClientModel;
import net.UDPServer;

public class TestUDPServer {
	public static final long BIND_TIME      = 200;
	public static final int port = 5000;	
	byte[] bytePacket = new byte[]{
			0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08
	};
	// Test initialize() method
	@Test
	public void testInitialize() throws InterruptedException{		
		try {
			FakeUDPClientModel clientModel=new FakeUDPClientModel();
			UDPServer receiver = new UDPServer(clientModel);
			receiver.initialize(port);
			Thread.sleep(BIND_TIME);		
			receiver.close();
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test (expected=AssertionError.class)
	public void testInitializeFail(){
		FakeUDPClientModel clientModel=new FakeUDPClientModel();
		UDPServer receiver = new UDPServer(clientModel);
		try {
			receiver.initialize(123);
			receiver.close();
		} catch (Exception e) {
			fail(e.toString());
		}	
	}
	
	// Test Server can receive data
	@Test
	public void testServerReceiveData() throws Exception{
		FakeUDPClientModel clientModel=new FakeUDPClientModel();
		//Server create
		UDPServer receiver = new UDPServer(clientModel);
		receiver.initialize(port);
		Thread.sleep(BIND_TIME);
		
		//Client create
		InetAddress testIP;
		testIP = InetAddress.getByName("127.0.0.1");
		Vector<InetAddress> IPData = new Vector<InetAddress>();
		IPData.add(testIP);	
		DatagramPacket sendPacket = new DatagramPacket(bytePacket, 
				       bytePacket.length, IPData.get(0), port);
		DatagramSocket socket = new DatagramSocket();
		socket.send(sendPacket);  
		Thread.sleep(1000);
		
		socket.close();
		receiver.close();
	}
	
	@Test
	public void testClose(){
		FakeUDPClientModel clientModel=new FakeUDPClientModel();
		UDPServerForTest receiver = new UDPServerForTest(clientModel);
		try {
			receiver.initialize(port);
			Thread.sleep(BIND_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		receiver.close();
		assertEquals(true,receiver.closeFlag);
	}
	
	//Test close() method
	class UDPServerForTest extends UDPServer{
		boolean closeFlag=false;
		public UDPServerForTest(FakeUDPClientModel clientModel) {
			super(clientModel);
		}
		@Override
		public void close(){
			super.close();
			closeFlag=true;
		}
	}
	
	class ClientModelMock extends FakeUDPClientModel{
		@Override
		public void set(byte[] bytes){
			assertEquals(bytePacket,bytes);
		}
	}
	
	class FakeUDPClientModel extends ClientModel{
		 public void set(byte [] packet){
		    }
	}
}