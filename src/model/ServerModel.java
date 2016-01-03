package model;

import java.awt.Point;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;

import model.game.Player;
import model.game.coder.ServerDecoder;
import model.game.coder.ServerEncoder;
import model.game.field.dynamic.Bullet;
import net.TCPServer;
import net.UDPClient;

public class ServerModel {
	private Room room;
	private Game game;
	private ServerDecoder decoder;
	private ServerEncoder encoder;
	private TCPServer tcpServer;
	private AtomicInteger atomicInteger;
	private UDPClient udpClient;

	public ServerModel() {
		// TODO Auto-generated constructor stub
		tcpServer = new TCPServer(this);
		udpClient = new UDPClient();
		atomicInteger = new AtomicInteger(1);
		decoder = new ServerDecoder(this);
		encoder = new ServerEncoder();
		room = new Room();
		game = new Game();
	}

	public boolean initialize(int port) {
		tcpServer.initialize(port);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		udpClient.initialize(tcpServer, port);
		return true;
	}

	public void setTotalTime(int second) throws IOException, InterruptedException {
		game.setTime(second);
		udpClient.send(encoder.setTotalTime(second).toString());
	}

	public void setPlayerNumber(int playernumber) throws IOException, InterruptedException {
		room.setPlayerNumber(playernumber);
		udpClient.send(encoder.setPlayerNumber(playernumber).toString());
	}

	public boolean startGame() throws IOException, InterruptedException {
		// call udp brocast
		for (int i = 0; i < room.getPlayerList().size(); i++) {
			if (room.getPlayerList().get(i).getID() % 2 == 0) {
				room.getPlayerList().get(i).setTeamID(2);
				game.getTeam(2).addPlayer(room.getPlayerList().get(i));
			} else if (room.getPlayerList().get(i).getID() % 2 == 1) {
				room.getPlayerList().get(i).setTeamID(1);
				game.getTeam(1).addPlayer(room.getPlayerList().get(i));
			}
		}
		udpClient.send(encoder.setTime(game.getTime()).toString());
		udpClient.send(encoder.startGame().toString());
		return true;
	}

	public boolean addPlayer(Player player) throws IOException, InterruptedException {
		assert !room.getPlayerList().contains(player) : "[ServerModel] addPlayer : player alreadt exist";
		room.addPlayer(player);
		udpClient.send(encoder.addPlayer(room).toString());
		return true;
	}

	public boolean removePalyer(Player player) throws IOException, InterruptedException {
		assert room.getPlayerList().contains(player) : "[ServerModel] removePalyer : player does not exist";
		room.removePlayer(player);
		udpClient.send(encoder.removePlayer(player).toString());
		return true;
	}

	public boolean setLocation(int id, Point point) throws IOException, InterruptedException {
		assert point != null : "[ServerModel] setLocation : Point location is null";
		int x = point.x, y = point.y;
		assert x > 0 && y > 0 : "[ServerModel] setLocation : location error x " + x + " y " + y;
		// call rule to move
		// if true then setLocation
		if (game.getPlayer(id) != null) {
			game.getPlayer(id).getCharacter().setLocation(point);
		} else {
			System.out.println("[ServerModel] setLocation player not exist playerID : " + id + " player : " + game.getPlayer(id));
		}
		udpClient.send(encoder.setLocation(game.getPlayer(id)).toString());
		return true;
	}

	public boolean fire(int id) throws IOException, InterruptedException {
		// new bullet
		// bullet direction = character direction
		udpClient.send(encoder.addBullet(new Bullet()).toString());
		udpClient.send(encoder.updateBullet(new Bullet()).toString());
		udpClient.send(encoder.removeBullet(new Bullet()).toString());

		// udpClient.send(encoder.removePlayer(game.getPlayer(1)).toString());
		// udpClient.send(encoder.gameOver(new
		// Result(game.getTeams())).toString());
		// udpClient.send(encoder.changeFlagColor(new Turf()).toString());
		// udpClient.send(encoder.setKillNumber(game.getPlayer(1)).toString());
		// udpClient.send(encoder.setMoney(game.getTeams()).toString());
		// udpClient.send(encoder.removeObstacle(new Obstacle()).toString());
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getSessionID() {
		return atomicInteger.getAndIncrement();
	}

}