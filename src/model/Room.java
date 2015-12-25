package model;

import java.util.ArrayList;

import model.game.Player;

public class Room{
	private ArrayList<Player> players;
	public Room(){
		players=new ArrayList<Player>();
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
	
	public void addPlayer(Player player){
		players.add(player);
	}
}
