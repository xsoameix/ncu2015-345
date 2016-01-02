package net;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.util.concurrent.atomic.AtomicInteger;

import model.ClientModel;

public class UDPServer extends Thread{
	private int port;
	final int SIZE = 8192;
    byte buffer[] = new byte[SIZE];
    private FakeUDPClientModel clientModel;
    private DatagramChannel    server;
    private AtomicInteger      state;
    private static final int STATE_INITIAL = 0;
    private static final int STATE_RUNNING = 1;
    public  static final int PIPE_EXIT     = 0;
    public  static final int PIPE_SYNC     = 1;
    private SinkChannel         ctrlIn;
    private SourceChannel       ctrlOut;
    private SinkChannel         syncIn;
    private SourceChannel       syncOut;
    private Thread              thread;
    
    public UDPServer(FakeUDPClientModel clientModel) {
    	this.clientModel=clientModel;
    	this.state = new AtomicInteger(STATE_INITIAL);
    }
    
    public void initialize(int port) throws Exception{ 
    	assert port>1024 && port<65536:"port over range";
    	this.port = port;
        thread = new UDPServer(clientModel);
    	this.start();
    }
    
    @Override
    public void run(){
        try {
            assert state.get() == STATE_INITIAL : "server should be initial";
          	Selector selector = Selector.open();
          	Pipe ctrlPipe = Pipe.open();
          	ctrlIn = ctrlPipe.sink();
            ctrlOut = ctrlPipe.source();
            ctrlOut.configureBlocking(false);
            ctrlOut.register(selector, SelectionKey.OP_READ);
            Pipe syncPipe = Pipe.open();
            syncIn = syncPipe.sink();
            syncOut = syncPipe.source();
            syncOut.configureBlocking(false);
            server = DatagramChannel.open();
            server.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            server.configureBlocking(false);
            InetSocketAddress addr = new InetSocketAddress(port);
            server.bind(addr);
            server.register(selector, SelectionKey.OP_READ);
            state.set(STATE_RUNNING);              
	    	
	    	while(true){
	    		int channels = selector.select();
	            if (channels == 0) continue;
	            for (SelectionKey key : selector.selectedKeys()){
	                if (key.isReadable()) {
	                	if (key.channel().equals(server)){
	                		ByteBuffer packet = ByteBuffer.allocate(SIZE);
		                    server.receive(packet);
		                    byte[] sendpacket = new byte[SIZE];
		                    sendpacket = packet.array();
		                    clientModel.set(sendpacket);
	                	}else if(key.channel().equals(ctrlOut)){
	                		server.close();
	                        selector.close();
	                        ByteBuffer buf =
	                            ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
	                        buf.putInt(PIPE_SYNC);
	                        buf.flip();
	                        syncIn.write(buf);
	                        return;
	                	}	
	                }
	             }
	             selector.selectedKeys().clear();
	    	}	    		
	    } catch (IOException e) {
	        e.printStackTrace();
	       	state.set(STATE_INITIAL);
	    }	
    } 
    
    public void close(){
    	try {
    		assert state.get() == STATE_RUNNING : "server should be running";
    		state.set(STATE_INITIAL);
    		ByteBuffer buf = ByteBuffer.allocate(4);
            buf.putInt(PIPE_EXIT);
            buf.flip();
            ctrlIn.write(buf);
            Selector selector;
			selector = Selector.open();			
            syncOut.register(selector, SelectionKey.OP_READ);
            for (boolean loop = true; loop; ) {
            	int channels = selector.select();
                if (channels == 0) continue;
                for (SelectionKey key : selector.selectedKeys()) {
                	if (key.isReadable()) {
                		try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                		loop = false;
                	}
                }
                selector.selectedKeys().clear();
            }
            buf.flip();
            buf.clear();
            syncOut.read(buf);
            syncIn.close();
            syncOut.close();
            buf.clear();
            ctrlOut.read(buf);
            ctrlIn.close();
            ctrlOut.close();
		} catch (IOException e) {
			e.printStackTrace();
			state.set(STATE_RUNNING);
		}
    }
}
