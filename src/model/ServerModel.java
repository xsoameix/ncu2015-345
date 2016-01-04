package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;

import model.game.Player;
import model.game.Result;
import model.game.Rule;
import model.game.Team;
import model.game.coder.ServerDecoder;
import model.game.coder.ServerEncoder;
import model.game.field.FieldObject;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.game.field.dynamic.Turf;
import model.game.field.map.MapBlock;
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
	private Rule rule;
	private TimeThread timeThread;
	private TurfThread turfThread;
	private AtomicInteger sessionAtomicInteger;
	private BulletThread bulletThread;

	public ServerModel() {
		// TODO Auto-generated constructor stub
		atomicInteger = new AtomicInteger(0);
		tcpServer = new TCPServer(this);
		udpClient = new UDPClient(tcpServer);
		sessionAtomicInteger = new AtomicInteger(1);
		game = new Game(atomicInteger);
		decoder = new ServerDecoder(this, atomicInteger);
		encoder = new ServerEncoder(atomicInteger);
		room = new Room();
		rule = game.getRule();
	}

	public boolean initialize(int port) {
		tcpServer.initialize(port);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		udpClient.initialize(port);
		return true;
	}

	public void setTotalTime(int second) throws IOException, InterruptedException {
		assert second >= 0 : "[ServerModel] setTotalTime second error : " + second;
		game.setTime(second);
		udpClient.send(encoder.setTotalTime(second).toString());
	}

	public void setTime(int second) throws IOException, InterruptedException {
		udpClient.send(encoder.setTime(second).toString());
	}

	public void setMoney(Vector<Team> teams) throws IOException, InterruptedException {
		udpClient.send(encoder.setMoney(teams).toString());
	}

	public void setPlayerNumber(int playernumber) throws IOException, InterruptedException {
		assert playernumber >= 2 && playernumber <= 6 : "[ServerModel] setPlayerNumber playernumber error : " + playernumber;
		room.setPlayerNumber(playernumber);
		udpClient.send(encoder.setPlayerNumber(playernumber).toString());
	}

	private void initPlayerRespawn() {
		if(game.getPlayerList().size()==1)
			game.getPlayer(1).setRespawn(1, 1);
		if(game.getPlayerList().size()==2)
			game.getPlayer(2).setRespawn(2, 1);
		if(game.getPlayerList().size()==3)
			game.getPlayer(3).setRespawn(1, 2);	
		if(game.getPlayerList().size()==4)
			game.getPlayer(4).setRespawn(18, 18);
		if(game.getPlayerList().size()==5)
			game.getPlayer(5).setRespawn(17, 18);
		if(game.getPlayerList().size()==6)
			game.getPlayer(6).setRespawn(18, 17);
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
		// init BulletThread
		// init TurfThread
		initPlayerRespawn();
		game.getPlayer(1).getCharacter().getLocation().x = 32;
		game.getPlayer(1).getCharacter().getLocation().y = 32;
		udpClient.send(encoder.setTime(game.getTime()).toString());
		udpClient.send(encoder.startGame().toString());
		// init TimeThread
		timeThread = new TimeThread(this);
		timeThread.start();
		turfThread = new TurfThread(game, game.getField(), game.getField().getMap(), rule);
		turfThread.start();
		bulletThread = new BulletThread(this);
		bulletThread.start();
		return true;
	}

	public void gameOver(Result result) throws IOException, InterruptedException {
		udpClient.send(encoder.gameOver(result).toString());
	}

	public boolean addPlayer(Player player) throws IOException, InterruptedException {
		assert player != null : "[ServerModel] addPlayer player is null : " + player;
		assert room.getPlayer(player.getID()) == null : "[ServerModel] addPlayer : player alreadt exist";
		room.addPlayer(player);
		udpClient.send(encoder.addPlayer(room).toString());
		udpClient.send(encoder.addPlayer(room).toString());
		udpClient.send(encoder.addPlayer(room).toString());
		udpClient.send(encoder.addPlayer(room).toString());
		return true;
	}

	public boolean removePalyer(Player player) throws IOException, InterruptedException {
		assert room.getPlayer(player.getID()) != null : "[ServerModel] removePalyer : player does not exist";
		room.removePlayer(player);
		udpClient.send(encoder.removePlayer(player).toString());
		return true;
	}

	public boolean setLocation(int id, Point point) throws IOException, InterruptedException {
		assert point != null : "[ServerModel] setLocation : Point location is null";
//		int x = point.x, y = point.y;
//		assert x >= 0 && y >= 0 : "[ServerModel] setLocation : location error x " + x + " y " + y;
		// call rule to move
		// if true then setLocation
		if (game.getPlayer(id) != null) {
//			if(point.x>=0&&point.y>=0&&
//					point.x<getGame().getField().getMap().getSize().width*MapBlock.getSize().width&&
//					point.y<getGame().getField().getMap().getSize().height*MapBlock.getSize().height){
//				setLocation(game.getPlayer(id).getCharacter(), point);
				game.getPlayer(id).getCharacter().setLocation(point);
//				if(!rule.MovingCheck(game.getPlayer(id).getCharacter()))
//					game.getPlayer(id).getCharacter().setLocation(original);
//			}
		} else {
			System.out.println("[ServerModel] setLocation player not exist playerID : " + id + " player : " + game.getPlayer(id));
		}
		udpClient.send(encoder.setLocation(game.getPlayer(id)).toString());
		return true;
	}

	private Point checkBlock[]={
			new Point(0, 0),
			new Point(1, 0),
			new Point(0, 1),
			new Point(1, 1),
	};
	public void setLocation(FieldObject object, Point point){
		Map map=game.getField().getMap();
		Point original=new Point(object.getLocation());
		//remove old
		for(int i=0; i<checkBlock.length; i++){
			Point now=new Point(original.x/MapBlock.getSize().width, original.y/MapBlock.getSize().height);
			now.translate(checkBlock[i].x, checkBlock[i].y);
			map.getMapBlock(now.x, now.y).removeFieldObject(object);
		}
		if(point.x<=0||point.y<=0||point.x>map.getSize().width*MapBlock.getSize().width||
				point.y>map.getSize().height*MapBlock.getSize().height){
			if(object instanceof Bullet)
				try {
					removeBullet((Bullet)object);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
		//update reside mapBlocks
		object.setLocation(point);
			}
//		Vector<FieldObject> collusionList=isCollusion(object);
//		if(!collusionList.isEmpty()){
//			int size=collusionList.size();
//			for(int i=0; i<size; i++)
//				collusion(object, collusionList.get(i), original);
//		}
	}
	public Vector<FieldObject> isCollusion(FieldObject object){
		Vector<FieldObject> collusionList=new Vector<FieldObject>();
		Point point=object.getLocation();
		for(int i=0; i<checkBlock.length; i++){
			Point now=new Point(point.x/MapBlock.getSize().width, point.y/MapBlock.getSize().height);
			now.translate(checkBlock[i].x, checkBlock[i].y);
			MapBlock mapBlock=getGame().getField().getMap().getMapBlock(now.x, now.y);
//			mapBlock.addFieldObject(object);
			for(FieldObject otherObject: mapBlock.getFieldObjects()){
				if(object.getRectangle().intersects(otherObject.getRectangle())){
					collusionList.add(otherObject);
				}
			}
		}
		return collusionList;
	}
	public void collusion(FieldObject object, FieldObject otherObject, Object original) {
		if(object instanceof Bullet){
			if(otherObject instanceof Bullet){
				try {
					removeBullet((Bullet)otherObject);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(otherObject instanceof Character){
				Player a=game.getPlayer(((Bullet)object).getPlayerID());
				Player b=game.getPlayer(((Character)otherObject).getPlayerID());
				if(a.getTeamID()!=b.getTeamID()){
					kill(a, b);
				}
			}
			else if(otherObject instanceof Obstacle){
				Obstacle obstacle=((Obstacle)otherObject);
				if(obstacle.isBreakable())
					removeObstacle(obstacle);
			}
			try {
				removeBullet((Bullet)object);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(object instanceof Character){
			if(otherObject instanceof Character){
				object.setLocation((Point) original);
			}
			else if(otherObject instanceof Obstacle){
				object.setLocation((Point) original);
			}
			else if(otherObject instanceof Turf){
				
			}
		}
	}
	private void removeObstacle(Obstacle obstacle) {		
		game.getField().removeObstacle(obstacle);
		udpClient.send(encoder.removeObstacle(obstacle).toString());
	}

	public void kill(Player player, Player killed){
		player.setKill(player.getKill()+1);
		killed.setDeath(killed.getDeath()+1);
		udpClient.send(encoder.setKillNumber(player).toString());
		udpClient.send(encoder.setDeathNumber(killed).toString());
	}

	public void removeBullet(Bullet bullet) throws IOException, InterruptedException {
		assert bullet!=null:"null bullet";
		game.getField().removeBullet(bullet);
		udpClient.send(encoder.removeBullet(bullet).toString());
	}

	public void updateBullet(Bullet bullet) throws IOException, InterruptedException {
		assert bullet!=null:"null bullet";
		udpClient.send(encoder.updateBullet(bullet).toString());
	}

	public boolean fire(int id) throws IOException, InterruptedException {
		// new bullet
		// bullet direction = character direction

		game.getPlayer(id).getCharacter().getDirection();
		Bullet bullet = new Bullet(id, sessionAtomicInteger.getAndIncrement(), game.getPlayer(id).getCharacter().getDirection(), game.getPlayer(id).getCharacter().getLocation());
		game.getField().addBullet(bullet);
		udpClient.send(encoder.addBullet(bullet).toString());

		// udpClient.send(encoder.updateBullet(new Bullet(1, 1)).toString());
		// udpClient.send(encoder.removeBullet(new Bullet(1, 1)).toString());

		// udpClient.send(encoder.removePlayer(game.getPlayer(1)).toString());
		// udpClient.send(encoder.gameOver(new
		// Result(game.getTeams())).toString());
		// udpClient.send(encoder.changeFlagColor(new Turf(1, new Point(576,
		// 32), 78)).toString());
		// Thread.sleep(500);
		// udpClient.send(encoder.setKillNumber(game.getPlayer(1)).toString());
		// udpClient.send(encoder.setMoney(game.getTeams()).toString());
		// udpClient.send(encoder.removeObstacle(new Obstacle(1, new Point(32,
		// 0))).toString());

		// game.getPlayer(id).getCharacter().setLocation(new Point(42, 32));
		// System.out.println("[ServerModel] fire " +
		// game.getPlayer(1).getCharacter().getLocation());
		// System.out.println("[ServerModel] fire " +
		// rule.MovingCheck(game.getPlayer(1).getCharacter()));
		// if (rule.MovingCheck(game.getPlayer(1).getCharacter()) == false) {
		// game.getPlayer(id).getCharacter().setLocation(new Point(32, 32));
		// }

		return true;
	}

	public void set(int id, byte[] packet) {
		assert packet != null : "[ServerModel] set : byte[] packet is null";
		JSONObject jsonObj = null;
		try {
//			System.out.println("[ServerModel] set " + new String(packet));
			jsonObj = new JSONObject(new String(packet));
		} catch (Exception e) {
			System.out.println("[ServerModel] set " + new String(packet));
			e.printStackTrace();
		}
		decoder.decode(id, jsonObj);
	}

	public Room getRoom() {
		return room;
	}

	public Game getGame() {
		return game;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getSessionID() {
		return sessionAtomicInteger.getAndIncrement();
	}

}