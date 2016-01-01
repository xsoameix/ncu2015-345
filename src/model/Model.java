package model;

import java.awt.Point;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import model.game.Player;
import model.game.Result;
import model.game.Team;
import model.game.coder.ClientDecoder;
import model.game.coder.ClientEncoder;
import model.game.field.Map;
import model.game.field.Turf;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.setting.Profile;
import net.TCPClient;

import org.json.JSONObject;

public class Model {
	// server side
	// private ServerModel serverModel

	// client side
	private TCPClient tcpClient;
	// private FakeUDPServer udpServer;
	private ServerModel serverModel;
	private Setting setting;
	private Room room;
	private Game game;
	private ClientEncoder encoder;
	private ClientDecoder decoder;
	private Player player;
	private Character character;
	private PlayPanel playPanel;

	public Model() {
		// TODO Auto-generated constructor stub
		room = new Room();
		game = new Game();
		setting = new Setting();
		encoder = new ClientEncoder();
		decoder = new ClientDecoder(this);
	}

	/* for client model start */
	// host
	protected boolean requestEstablishRoom(int port) {
		serverModel = new ServerModel();
		return serverModel.initialize(port);
	}

	// client
	protected boolean requestEnterRoom(String ip, int port) {
		tcpClient = new TCPClient();
		// udpServer = new FakeUDPServer();
		try {
			tcpClient.initialize(InetAddress.getByName(ip), port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// udp init
		character = new Character();
		player = new Player(character, setting.getProfile());
		player.setProfile(setting.getProfile());
		tcpClient.send(encoder.requestAddPlayer(player).toString().getBytes(StandardCharsets.UTF_8));
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
		Point location = new Point();
		location.x = x;
		location.y = y;
		JSONObject content = encoder.encodeObject("requestSetLocation", location);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	/* for client model end */

	/* for UDP start */
	public void setTotalTime(int time) {
		game.setTime(time);
	}

	public synchronized void setMoney(Vector<Team> teams) {
		for (int i = 0; i < teams.size(); i++) {
			Team tmp = teams.get(i);
			if (game.getTeam(tmp.getID()) != null) {
				game.getTeam(tmp.getID()).updateMoney(tmp);
			}
		}
	}

	public synchronized void setKillNumber(Player player) {
		int newPlayerID = player.getTeamID();
		game.getTeam(newPlayerID).getPlayer(newPlayerID).setKill(player.getKill());
	}

	public synchronized void setPlayerNumber(int playnumber) {
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

	public synchronized boolean addBullet(Bullet bullet) {
		synchronized (game.getField().getBulletList()) {
			game.getField().getBulletList().add(bullet);
			return true;
		}
	}

	public synchronized void updateBullet(Bullet bullet) {
		synchronized (game.getField().getBulletList()) {
			Bullet oldBullet = game.getField().getBullet(bullet.getID());
			oldBullet.setDirection(bullet.getDirection());
			oldBullet.setLocation(bullet.getLocation());
		}
	}

	public synchronized boolean removeBullet(Bullet bullet) {
		synchronized (game.getField().getBulletList()) {
			for (int i = 0; i < game.getField().getBulletList().size(); i++) {
				Bullet tmp = game.getField().getBulletList().get(i);
				if (tmp != null && tmp.getID() == bullet.getID()) {
					game.getField().getBulletList().remove(tmp);
				}
			}
			return true;
		}
	}

	public void changeTurfColor(Turf turf) {
		// change turf color

	}

	public synchronized boolean removeObstacle(Obstacle obstacle) {
		synchronized (game.getField().getObstacleList()) {
			for (int i = 0; i < game.getField().getObstacleList().size(); i++) {
				Obstacle tmp = game.getField().getObstacleList().get(i);
				if (tmp != null && tmp.getID() == obstacle.getID()) {
					game.getField().getObstacleList().remove(tmp);
				}
			}
			return true;
		}
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

	public void setLocation(Player Player) {
		Character oldCharacter = game.getTeam(player.getTeamID()).getPlayer(player.getTeamID()).getCharacter();
		oldCharacter.setDirection(player.getCharacter().getDirection());
		oldCharacter.setLocation(player.getCharacter().getLocation());
	}

	/* for UDP end */

	public void setPlayPanel(PlayPanel playPanel) {
		this.playPanel = playPanel;
	}

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
