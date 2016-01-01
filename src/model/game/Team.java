package model.game;

import java.util.Vector;

public class Team {
	private int ID;
	private Vector<Player> players;
	private int money;
	private int landNumber;
	
	public int getID(){
		return ID;
	}
	
	public void setID(int id){
		this.ID = id;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	public int getLanNumber(){
		return landNumber;
	}
	
	public void setLandNumber(int landNumber){
		this.landNumber = landNumber;
	}
	
	public Vector<Player> getPlayerList(){
		return players;
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public void removePlayer(Player player){
		players.remove(player);
	}
}
