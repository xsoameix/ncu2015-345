package model;

import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Obstacle;
import model.setting.Profile;

public class Model {
	// server side
	// private ServerModel serverModel

	// client side
	// private TCPClient tcpClient;
	// private UDPServer udpServer;
	private Setting setting;
	private Room room;
	private Game game;
	private Profile profile;

	/* for client model start */
	// host
	protected boolean requestEstablishRoom(int port) {
		// room=new Room();
		/*
		 * tcpServer=new TCPServer();
		 * 
		 * 
		 * enterRoom();
		 */
		return true;
	}

	// client
	protected boolean requestEnterRoom(String ip, int port) {
		// room=new Room();//get room from server? or get data from server and
		// set to new room?
		// Player player=new Player();
		// player.setProfile(setting.getProfile());
		// room.addPlayer(player);
		/*
		 * tcpClient=new TCPClient();
		 */
		return true;
	}

	protected void requestFire() {

	}

	protected void requestStartGame() {

	}

	protected void requestSetTotalTime(int time) {

	}

	protected void requestSetPlayerNumber(int number) {

	}

	protected void requestSetLocation(int x, int y) {

	}

	protected void requestKeyInput(int key) {

	}

	/* for client model end */

	/* for UDP start */
	public void setTotalTime(int second) {

	}

	public void setPlayerNumber(int playnumber) {

	}

	public boolean addPlayer(Profile profile) {
		return true;
	}

	public boolean removePlayer(Profile profile) {
		return true;
	}

	public void gameOver(Result result) {

	}

	public void setTime(int second) {

	}

	public boolean addBullet(Bullet bullet) {
		return true;
	}

	public boolean removeBullet(Bullet bullet) {
		return true;
	}

	public boolean removeObstacle(Obstacle obstale) {
		return true;
	}

	public void setMap(Map map) {

	}

	public void set(Byte[] packet) {

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
		return -1;
	}
}
