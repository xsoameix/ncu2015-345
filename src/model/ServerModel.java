package model;

import java.awt.Point;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;

import model.game.Player;
import model.game.coder.ServerDecoder;
import net.FakeServerModel;
import net.TCPServer;
import net.UDPClient;

public class ServerModel {
	private Room room;
	private Game game;
	private ServerDecoder decoder;
	private TCPServer tcpServer;
	private AtomicInteger atomicInteger;
	private UDPClient udpClient;

	public ServerModel() {
		// TODO Auto-generated constructor stub
		tcpServer = new TCPServer(new FakeServerModel());
		udpClient = new UDPClient();
		atomicInteger = new AtomicInteger();
	}

	public boolean initialize(int port) {
		tcpServer.initialize(port);
		udpClient.initialize(port);
		return true;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setTotalTime(int second) {
		game.setTime(second);
	}

	public void setPlayerNumber(int playernumber) {
		room.setPlayerNumber(playernumber);
	}

	public boolean startGame() {
		// call udp brocast
		return true;
	}

	public boolean addPlayer(Player player) {
		assert !room.getPlayerList().contains(player) : "[ServerModel] addPlayer : player alreadt exist";
		room.addPlayer(player);
		return true;
	}

	public boolean removePalyer(Player player) {
		assert room.getPlayerList().contains(player) : "[ServerModel] removePalyer : player does not exist";
		room.removePlayer(player);
		return true;
	}

	public boolean setLocation(int id, Point point) {
		assert point != null : "[ServerModel] setLocation : Point location is null";
		int x = point.x, y = point.y;
		assert x > 0 && y > 0 : "[ServerModel] setLocation : location error x " + x + " y " + y;
		// call rule to move
		// if true then setLocation
		game.getPlayer(id).getCharacter().setLocation(point);
		return true;
	}

	public boolean fire(int id) {
		// new bullet
		// bullet direction = character direction
		return true;
	}

	public void set(int id, byte[] packet) {
		assert packet != null : "[ServerModel] set : byte[] packet is null";
		JSONObject jsonObj = null;
		try {
			System.out.println("[ServerModel] set " + new String(packet));
			jsonObj = new JSONObject(new String(packet));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		decoder.decode(id, jsonObj);
	}

	public int getSessionID() {
		return atomicInteger.get();
	}

}