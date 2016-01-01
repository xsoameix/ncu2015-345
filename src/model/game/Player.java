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

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public int getKill() {
		return kill;
	}

	public void setKill(int kill) {
		this.kill = kill;
	}

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}
