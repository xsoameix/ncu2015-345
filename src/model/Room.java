package model;

import java.util.Vector;

import model.game.Player;

public class Room {
	private Vector<Player> players;
	private int playerNumber;

	public Room() {
		this.players = new Vector<Player>();
	}

	public Vector<Player> getPlayerList() {
		return players;
	}

	public synchronized void addPlayer(Player player) {
		synchronized (players) {
			players.add(player);
		}
	}

	public synchronized void addPlayer(Room room) {
		synchronized (players) {
			for (int i = 0; i < room.getPlayerList().size(); i++) {
				boolean exist = false;
				for (int j = 0; j < players.size(); j++) {
					if (players.get(j).getID() == room.getPlayerList().get(i).getID()) {
						exist = true;
					}
				}
				if (exist == false) {
					this.players.add(room.getPlayerList().get(i));
				}
			}
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