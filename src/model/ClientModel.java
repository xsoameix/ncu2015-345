package model;

import java.awt.Point;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import org.json.JSONObject;

import model.game.Player;
import model.game.Result;
import model.game.Team;
import model.game.coder.ClientDecoder;
import model.game.coder.ClientEncoder;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.game.field.dynamic.Turf;
import model.setting.Profile;
import net.TCPClient;
import net.UDPServer;
import view.play.PlayPanel;

public class ClientModel {
	private TCPClient tcpClient;
	private UDPServer udpServer;
	private ServerModel serverModel;
	private Setting setting;
	private Room room;
	private Game game;
	private ClientEncoder encoder;
	private ClientDecoder decoder;
	private Player player;
	private Character character;
	private PlayPanel playPanel;

	public ClientModel() {
		room = new Room();
		game = new Game();
		setting = new Setting();
		encoder = new ClientEncoder();
		decoder = new ClientDecoder(this);
	}

	/* for outside API start */
	// host
	public boolean requestEstablishRoom(int port) {
		serverModel = new ServerModel();
		return serverModel.initialize(port);
	}

	// client
	public boolean requestEnterRoom(String ip, int port) {
		tcpClient = new TCPClient();
		udpServer = new UDPServer(this);
		try {
			tcpClient.initialize(InetAddress.getByName(ip), port);
			udpServer.initialize(port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		character = new Character();
		player = new Player(character, setting.getProfile());
		player.setProfile(setting.getProfile());
		tcpClient.send(encoder.requestAddPlayer(player).toString().getBytes(StandardCharsets.UTF_8));
		return true;
	}

	public void requestFire() {
		JSONObject content = encoder.encodeObject("requestFire", null);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	public void requestStartGame() {
		JSONObject content = encoder.encodeObject("requestStartGame", null);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	public void requestSetTotalTime(int time) {
		JSONObject content = encoder.encodeObject("requestSetTotalTime", time);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	public void requestSetPlayerNumber(int number) {
		JSONObject content = encoder.encodeObject("requestSetPlayerNumber", number);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	public void requestSetLocation(int x, int y) {
		Point location = new Point();
		location.x = x;
		location.y = y;
		JSONObject content = encoder.encodeObject("requestSetLocation", location);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	/* for outside API end */

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

	public boolean addPlayer(Room room) {
		this.room.addPlayer(room);
		return true;
	}

	public boolean removePlayer(Player player) {
		room.removePlayer(player);
		return true;
	}

	public void gameOver(Result result) {
		playPanel.gameOver(result);
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
		if (game.getTurf(turf.getID()) != null) {
			game.getTurf(turf.getID()).setID(turf.getID());
		}

	}

	public synchronized boolean removeObstacle(Obstacle obstacle) {
		synchronized (game.getField().getObstacles()) {
			for (int i = 0; i < game.getField().getObstacles().size(); i++) {
				Obstacle tmp = game.getField().getObstacles().get(i);
				if (tmp != null && tmp.getID() == obstacle.getID()) {
					game.getField().getObstacles().remove(tmp);
				}
			}
			return true;
		}
	}

	public void setMap(Map map) {
		game.getField().setMap(map);
	}

	public void set(byte[] packet) {
		JSONObject jsonObj = null;
		try {
			System.out.println("[ClientModel] set " + new String(packet));
			jsonObj = new JSONObject(new String(packet));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		decoder.decode(jsonObj);
	}

	public void startGame() {
		for (int i = 0; i < room.getPlayerList().size(); i++) {
			if (room.getPlayerList().get(i).getID() % 2 == 0) {
				game.getTeam(2).addPlayer(room.getPlayerList().get(i));
			} else if (room.getPlayerList().get(i).getID() % 2 == 1) {
				game.getTeam(1).addPlayer(room.getPlayerList().get(i));
			}
		}
		// playPanel.startGame();
	}

	public void setLocation(Player newPlayer) {
		if (game.getPlayer(newPlayer.getID()) != null) {
			Character oldCharacter = game.getPlayer(newPlayer.getID()).getCharacter();
			oldCharacter.setDirection(newPlayer.getCharacter().getDirection());
			oldCharacter.setLocation(newPlayer.getCharacter().getLocation());
		} else {
			System.out.println("[ClientModel] setLocation player is null player ID : " + newPlayer.getID());
		}

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
