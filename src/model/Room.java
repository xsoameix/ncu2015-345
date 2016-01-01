package model;

import java.util.ArrayList;

import model.game.Player;

public class Room {
	private ArrayList<Player> players;
	private int playerNumber;

	public Room() {
		this.players = new ArrayList<Player>();
	}

	public ArrayList<Player> getPlayerList() {
		return players;
	}

	public synchronized void addPlayer(Player player) {
		synchronized (players) {
			this.players.add(player);
		}
	}

	public synchronized void removePlayer(Player player) {
		synchronized (players) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getID() == player.getID()) {
					this.players.remove(players.get(i));
				}
			}
		}
	}

	public synchronized void setPlayerNumber(int number) {
		this.playerNumber = number;
	}

	public int getPlayerNumber() {
		return this.playerNumber;
	}
}
