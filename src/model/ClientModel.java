package model;

public class ClientModel{
	//server side
//	private TCPServer tcpServer;
//	private UDPClient udpClient;
	
	//client side
//	private TCPClient tcpClient;
//	private UDPServer udpServer;
	
	private Setting setting;
	private Room room;
	private Game game;
	
	//host
	public void establishRoom() {
//		room=new Room();
		/*	tcpServer=new TCPServer();
		 * 	
		 * 	
		 * 	enterRoom();
		 * */
	}
	//client
	public void enterRoom() {
//		room=new Room();//get room from server? or get data from server and set to new room?
//		Player player=new Player();
//		player.setProfile(setting.getProfile());
//		room.addPlayer(player);
		/*	tcpClient=new TCPClient();
		 * 	
		 * 
		 * 
		 * */
	}
	public Room getRoom() {
		return room;
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

}
