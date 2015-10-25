package model;

public class Room {
	private boolean isHost;
	private Game game;
	
	public boolean isHost() {
		return isHost;
	}
	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}
	public Game getGame() {
		return game;
	}
	public void establishGame() {
		this.game = new Game();
	}
}
