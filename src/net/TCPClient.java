package net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

    private static final int STATE_INITIAL = 0;
    private static final int STATE_CONNECTED = 1;

    private Socket socket;
    private DataOutputStream out;
    private int state = STATE_INITIAL;

    public boolean initialize(InetAddress serverip, int port) {
        try {
            assert state == STATE_INITIAL : "client should be initial";
            socket = new Socket(serverip, port);
            out = new DataOutputStream(socket.getOutputStream());
            state = STATE_CONNECTED;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void send(byte body[]) {
        try {
            assert state == STATE_CONNECTED : "client should be connected";
            out.writeInt(body.length);
            out.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            assert state == STATE_CONNECTED : "client should be connected";
            out.close();
            socket.close();
            state = STATE_INITIAL;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
