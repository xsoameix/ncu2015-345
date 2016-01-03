package model;

import java.awt.Point;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

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
	private Player individual;
	private Character character;
	private PlayPanel playPanel;
	private AtomicInteger atomicInteger;

	public ClientModel() {
		atomicInteger = new AtomicInteger(0);
		playPanel = new PlayPanel();
		game = new Game(atomicInteger);
		room = new Room();
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
		individual = new Player(character, setting.getProfile());
		individual.setProfile(setting.getProfile());
		tcpClient.send(encoder.requestAddPlayer(individual).toString().getBytes(StandardCharsets.UTF_8));
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
		assert time >= 0 : "[ClientModel] requestSetTotalTime time error : " + time;
		JSONObject content = encoder.encodeObject("requestSetTotalTime", time);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	public void requestSetPlayerNumber(int number) {
		assert number >= 2 && number <= 6 : "[ClientModel] requestSetPlayerNumber number error : " + number;
		JSONObject content = encoder.encodeObject("requestSetPlayerNumber", number);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	public void requestSetLocation(int x, int y) {
		assert x >= 0 && y >= 0 : "[ClientModel] requestSetLocation x&y error : [" + x + "] [" + y + "]";
		Point location = new Point();
		location.x = x;
		location.y = y;
		JSONObject content = encoder.encodeObject("requestSetLocation", location);
		tcpClient.send(content.toString().getBytes(StandardCharsets.UTF_8));
	}

	/* for outside API end */

	public void setTotalTime(int time) {
		assert time >= 0 : "[ClientModel] setTotalTime time error : " + time;
		game.setTime(time);
	}

	public synchronized void setMoney(Vector<Team> teams) {
		assert teams != null : "[ClientModel] setMoney teams is null ";
		for (int i = 0; i < teams.size(); i++) {
			Team tmp = teams.get(i);
			if (game.getTeam(tmp.getID()) != null) {
				game.getTeam(tmp.getID()).updateMoney(tmp);
			}
		}
	}

	public synchronized void setKillNumber(Player player) {
		assert player != null : "[ClientModel] setKillNumber player is null : " + player;
		int newPlayerID = player.getID();
		assert game.getPlayer(newPlayerID) != null : "[ClientModel] setKillNumber getPlayer is null playerID : " + newPlayerID;
		game.getPlayer(newPlayerID).setKill(player.getKill());
	}

	public synchronized void setPlayerNumber(int playnumber) {
		assert playnumber >= 2 && playnumber <= 6 : "[ClientModel] setPlayerNumber playnumber error : " + playnumber;
		room.setPlayerNumber(playnumber);
	}

	public synchronized boolean addPlayer(Room room) {
		assert room != null : "[ClientModel] addPlayer room is null";
		if (individual.getID() == 0) {
			individual.setID(room.getPlayerList().size());
		}
		this.room.addPlayer(room);
		for (int i = 0; i < room.getPlayerList().size(); i++) {
			playPanel.addPlayer(room.getPlayerList().get(i));
		}
		return true;
	}

	public synchronized boolean removePlayer(Player player) {
		assert player != null : "[ClientModel] removePlayer player is null";
		room.removePlayer(player);
		playPanel.removePlayer(player);
		return true;
	}

	public void gameOver(Result result) {
		assert result != null : "[ClientModel] gameOver result is null";
		playPanel.gameOver(result);
	}

	public void setTime(int time) {
		assert time >= 0 : "[ClientModel] setTime time error : " + time;
		game.setTime(time);
	}

	public synchronized boolean addBullet(Bullet bullet) {
		assert bullet != null : "[ClientModel] addBullet bullet is null";
		synchronized (game.getField().getBulletList()) {
			game.getField().getBulletList().add(bullet);
			this.playPanel.addBullet(bullet);
			return true;
		}
	}

	public synchronized void updateBullet(Bullet bullet) {
		assert bullet != null : "[ClientModel] updateBullet bullet is null";
		synchronized (game.getField().getBulletList()) {
			Bullet oldBullet = game.getField().getBullet(bullet.getID());
			assert oldBullet != null : "[ClientModel] updateBullet getBullet is null bulletID : " + bullet.getID();
			oldBullet.setDirection(bullet.getDirection());
			oldBullet.setLocation(bullet.getLocation());
		}
	}

	public synchronized boolean removeBullet(Bullet bullet) {
		assert bullet != null : "[ClientModel] removeBullet bullet is null";
		synchronized (game.getField().getBulletList()) {
			for (int i = 0; i < game.getField().getBulletList().size(); i++) {
				Bullet tmp = game.getField().getBulletList().get(i);
				if (tmp != null && tmp.getID() == bullet.getID()) {
					game.getField().getBulletList().remove(tmp);
					this.playPanel.removeBullet(bullet);
				}
			}
			return true;
		}
	}

	public void changeTurfColor(Turf turf) {
		assert turf != null : "[ClientModel] changeTurfColor turf is null";
		assert game.getTurf(turf.getID()) != null : "[ClientModel] changeTurfColor getTurf is null turfID : " + turf.getID();
		if (game.getTurf(turf.getID()) != null) {
			game.getTurf(turf.getID()).setTeamID(turf.getTeamID());
		}
	}

	public synchronized boolean removeObstacle(Obstacle obstacle) {
		assert obstacle != null : "[ClientModel] removeObstacle obstacle is null";
		synchronized (game.getField().getObstacles()) {
			for (int i = 0; i < game.getField().getObstacles().size(); i++) {
				Obstacle tmp = game.getField().getObstacles().get(i);
				if (tmp != null && tmp.getID() == obstacle.getID()) {
					game.getField().getObstacles().remove(tmp);
					this.playPanel.removeObstacle(obstacle);
				}
			}
			return true;
		}
	}

	public void set(byte[] packet) {
		assert packet != null : "[ClientModel] set packet is null";
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

	private void initPlayerRespawn() {
		game.getPlayer(1).setRespawn(1, 1);
		// game.getPlayer(2).setRespawn(2, 1);
		// game.getPlayer(3).setRespawn(1, 2);
		// game.getPlayer(4).setRespawn(18, 18);
		// game.getPlayer(5).setRespawn(17, 18);
		// game.getPlayer(6).setRespawn(18, 17);
	}

	public void startGame() {
		for (int i = 0; i < room.getPlayerList().size(); i++) {
			if (room.getPlayerList().get(i).getID() % 2 == 0) {
				room.getPlayerList().get(i).setTeamID(2);
				game.getTeam(2).addPlayer(room.getPlayerList().get(i));
			} else if (room.getPlayerList().get(i).getID() % 2 == 1) {
				room.getPlayerList().get(i).setTeamID(1);
				game.getTeam(1).addPlayer(room.getPlayerList().get(i));
			}
		}
		initPlayerRespawn();
		playPanel.startGame();
	}

	public synchronized void setLocation(Player newPlayer) {
		assert newPlayer != null : "[ClientModel] setLocation newPlayer is null";
		assert game.getPlayer(newPlayer.getID()) != null : "[ClientModel] setLocation getPlayer is null playerID : " + newPlayer.getID();
		if (game.getPlayer(newPlayer.getID()) != null) {
			Character oldCharacter = game.getPlayer(newPlayer.getID()).getCharacter();
			oldCharacter.setDirection(newPlayer.getCharacter().getDirection());
			oldCharacter.setLocation(newPlayer.getCharacter().getLocation());
		} else {
			System.out.println("[ClientModel] setLocation player is null player ID : " + newPlayer.getID());
		}

	}

	/* for UDP end */

	public void setMap(Map map) {
		game.getField().setMap(map);
	}

	public Player getIndividual() {
		return individual;
	}

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
