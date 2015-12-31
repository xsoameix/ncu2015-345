package model;

import java.awt.Point;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import model.game.Player;
import model.game.coder.ClientDecoder;
import model.game.coder.ClientEncoder;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Obstacle;
import model.setting.Profile;
import net.FakeTCPClient;
import net.FakeTCPServer;

public class Model {
	// server side
	// private ServerModel serverModel

	// client side
	private FakeTCPClient tcpClient;
	private FakeTCPServer tcpServer;
	private Setting setting;
	private Room room;
	private Game game;
	private Profile profile;
	private ClientEncoder Encoder;
	private ClientDecoder Decoder;

	/* for client model start */
	// host
	protected boolean requestEstablishRoom(int port) {
		room = new Room();
		Player player = new Player();
		tcpServer = new FakeTCPServer();
		room.addPlayer(player);
		return true;
	}

	// client
	protected boolean requestEnterRoom(String ip, int port) {
		room = new Room();
		Player player = new Player();
		player.setProfile(setting.getProfile());
		room.addPlayer(player);
		tcpClient = new FakeTCPClient();
		return true;
	}

	protected void requestFire() {
		JSONObject content = Encoder.encodeObject("requestFire", null);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestStartGame() {
		JSONObject content = Encoder.encodeObject("requestStartGame", null);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestSetTotalTime(int time) {
		JSONObject content = Encoder.encodeObject("requestSetTotalTime", time);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestSetPlayerNumber(int number) {
		JSONObject content = Encoder.encodeObject("requestSetPlayerNumber", number);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestSetLocation(int x, int y) {
		Point location = null;
		location.x = x;
		location.y = y;
		JSONObject content = Encoder.encodeObject("requestSetLocation", location);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	/*
	protected void requestKeyInput(int key) {

	}
	*/

	/* for client model end */

	/* for UDP start */
	public void setTotalTime(int time) {
		game.setTime(time);
	}

	public void setPlayerNumber(int playnumber) {
		room.setPlayerNumber(playnumber);
	}

	public boolean addPlayer(Player player) {
		room.addPlayer(player);
		return true;
	}

	public boolean removePlayer(Player player) {
		room.removePlayer(player);
		return true;
	}

	public void gameOver(Result result) {

	}

	public void setTime(int time) {
		game.setTime(time);
	}

	public boolean addBullet(Bullet bullet) {
		game.getField().getBulletList().add(bullet);
		return true;
	}

	public boolean removeBullet(Bullet bullet) {
		game.getField().getBulletList().remove(bullet);
		return true;
	}

	public boolean removeObstacle(Obstacle obstale) {
		game.getField().getObstacleList().remove(obstale);
		return true;
	}

	public void setMap(Map map) {
		game.getField().setMap(map);
	}

	public void set(Byte[] packet) {
		JSONObject jsonObj = null;
	    byte[] str = packet.toString().getBytes(StandardCharsets.UTF_8);
		try {
			String content = new String(str, StandardCharsets.UTF_8);
			jsonObj = new JSONObject(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Decoder.decode(jsonObj);
	}

	public void startGame() {
		
	}

	public void setLocation(int x, int y) {
		
	}

	/* for UDP end */

	public Room getRoom() {
		return room;
	}

	public Setting getSetting() {
		return setting;
	}

	public Game getGame() {
		return game;
	}

	public Profile getProfile() {
		return profile;
	}

	public int getTime() {
		return game.getTime();
	}
}
