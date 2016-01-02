package net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

public class FakeTCPServerModel {
    public Vector<InetAddress> getIPTable(){
    	Vector<InetAddress> IPTable = new Vector<InetAddress>();
    	try {
			IPTable.add(InetAddress.getByName("127.0.0.1")) ;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	return IPTable;
    }
}
