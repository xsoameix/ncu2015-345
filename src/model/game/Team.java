package model.game;

import java.util.Vector;

public class Team {
	private int ID;// Team ID Time1 = 1 Time2 = 2
	private Vector<Player> players;
	private int money;
	private int turfNumber = 0;

	public Team(int ID) {
		// TODO Auto-generated constructor stub
		players = new Vector<>();
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public synchronized void setID(int id) {
		this.ID = id;
	}

	public int getMoney() {
		return money;
	}

	public synchronized void setMoney(int money) {
		this.money = money;
	}

	public int getTurfNumber() {
		return turfNumber;
	}

	public synchronized void setTurfNumber(int turfNumber) {
		this.turfNumber = turfNumber;
	}

	public Vector<Player> getPlayerList() {
		return players;
	}

	public synchronized void addPlayer(Player player) {
		synchronized (player) {
			players.add(player);
		}
	}

	public synchronized Player getPlayer(int ID) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getID() == ID) {
				return players.get(i);
			}
		}
		return null;
	}

	public synchronized void removePlayer(Player player) {
		synchronized (player) {
			players.remove(player);
		}
	}

	public synchronized void updateMoney(Team team) {
		this.money = team.getMoney();
		synchronized (team.getPlayerList()) {
			for (int i = 0; i < team.getPlayerList().size(); i++) {
				Player tmp = team.getPlayerList().get(i);
				if (getPlayer(tmp.getID()) != null) {
					getPlayer(tmp.getID()).setMoney(tmp.getMoney());
				}
			}
		}
	}
}
