package model;

import java.util.ArrayList;

import model.game.Player;

public class Room{
	private ArrayList<Player> players;
	private int playerNumber;
	
	public Room(){
		this.players = new ArrayList<Player>();
	}
	
//	public Game getGame() {
//		return game;
//	}
//	public void establishGame() {
//		this.game = new Game();
//		Player[] players = new Player[this.players.size()];
//		players=this.players.toArray(players);
//		game.setPlayers(players);
////		game.setMap(Maps.maps[0]);
//		
//	}
	
	public ArrayList<Player> getPlayerList(){
		return players;
	}
	
	public void addPlayer(Player player){
		this.players.add(player);
	}
	
	public void removePlayer(Player player){
		this.players.remove(player);
	}
	
	public void setPlayerNumber(int number){
		this.playerNumber = number;
	}
	
	public int getPlayerNumber(){
		return this.playerNumber;
	}
}
