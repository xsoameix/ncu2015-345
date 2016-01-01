package model;

import java.awt.Point;
import java.io.CharConversionException;
import java.nio.charset.StandardCharsets;

import model.game.Player;
import model.game.Result;
import model.game.coder.ClientDecoder;
import model.game.coder.ClientEncoder;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.setting.Profile;

import org.json.JSONObject;

public class Model {
	// server side
	// private ServerModel serverModel

	// client side
	private FakeTCPClient tcpClient;
	private FakeUDPServer udpServer;
	private ServerModel serverModel;
	private Setting setting;
	private Room room;
	private Game game;
	private ClientEncoder encoder;
	private ClientDecoder decoder;
	private Player player;
	private Character character;

	public Model() {
		// TODO Auto-generated constructor stub
		room = new Room();
		game = new Game();
		setting = new Setting();
		encoder = new ClientEncoder();
		decoder = new ClientDecoder();
	}

	/* for client model start */
	// host
	protected boolean requestEstablishRoom(int port) {
		tcpClient = new FakeTCPClient();
		udpServer = new FakeUDPServer();
		serverModel = new ServerModel();
		return true;
	}

	// client
	protected boolean requestEnterRoom(String ip, int port) {
		character = new Character();
		player = new Player(character, setting.getProfile());
		player.setProfile(setting.getProfile());
		room.addPlayer(player);
		tcpClient = new FakeTCPClient();
		return true;
	}

	protected void requestFire() {
		JSONObject content = encoder.encodeObject("requestFire", null);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestStartGame() {
		JSONObject content = encoder.encodeObject("requestStartGame", null);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestSetTotalTime(int time) {
		JSONObject content = encoder.encodeObject("requestSetTotalTime", time);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestSetPlayerNumber(int number) {
		JSONObject content = encoder.encodeObject("requestSetPlayerNumber", number);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	protected void requestSetLocation(int x, int y) {
		Point location = null;
		location.x = x;
		location.y = y;
		JSONObject content = encoder.encodeObject("requestSetLocation", location);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	/*
	 * protected void requestKeyInput(int key) {
	 * 
	 * }
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
		decoder.decode(jsonObj);
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
		return setting.getProfile();
	}

	public int getTime() {
		return game.getTime();
	}
}
