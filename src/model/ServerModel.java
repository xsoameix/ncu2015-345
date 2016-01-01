package model;

import java.awt.Point;
import java.nio.charset.StandardCharsets;

import model.game.Player;
import model.game.coder.ServerDecoder;
import model.game.field.dynamic.Character;
import model.setting.Profile;
import net.TCPServer;

import org.json.JSONObject;

public class ServerModel {
	private Room room;
	private Profile profile;
	private Game game;
	private Player player;
	private Character character;
	private ServerDecoder decoder;
	private FakeTCPServer tcpServer;
	private FakeUDPClient udpClient;

	public ServerModel() {
		// TODO Auto-generated constructor stub
		tcpServer = new TCPServer();
		udpClient = new FakeUDPClient();
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
		return true;
	}

	public boolean addPlayer(Player player) {
		assert !room.getPlayerList().contains(player) : "[ServerModel] addPlayer : player alreadt exist";
		room.addPlayer(player);
		return true;
	}

	public boolean removePalyer(Player player) {
		room.removePlayer(player);
		return true;
	}

	public boolean setLocation(int x, int y) {
		Point location = null;
		location.x = x;
		location.y = y;
		character.setLocation(location);
		return true;
	}

	public boolean fire() {
		// character.newbullet();
		return true;
	}

	public void set(byte[] packet) {     
		JSONObject jsonObj = null;
		byte[] str = packet.toString().getBytes(StandardCharsets.UTF_8);
		try {
			String content = new String(str, StandardCharsets.UTF_8);
			jsonObj = new JSONObject(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		decoder.decode(jsonObj);
	}

}