package model.game;

import model.game.field.dynamic.Character;
import model.setting.Profile;

public class Player {
	private Profile profile;

	private int ID;
	private int teamID;

	private Character character;
	private int kill;
	private int death;
	private int money;

	public Player(Character character, Profile profile) {
		this.character = character;
		this.profile = profile;
		this.kill = 0;
		this.death = 0;
		this.money = 0;
	}

	public Character getCharacter() {
		return character;
	}

	public synchronized void setCharacter(Character character) {
		this.character = character;
	}

	public Profile getProfile() {
		return profile;
	}

	public synchronized void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getID() {
		return ID;
	}

	public synchronized void setID(int iD) {
		ID = iD;
	}

	public int getTeamID() {
		return teamID;
	}

	public synchronized void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public int getKill() {
		return kill;
	}

	public synchronized void setKill(int kill) {
		this.kill = kill;
	}

	public synchronized int getDeath() {
		return death;
	}

	public synchronized void setDeath(int death) {
		this.death = death;
	}

	public int getMoney() {
		return money;
	}

	public synchronized void setMoney(int money) {
		this.money = money;
	}

}
