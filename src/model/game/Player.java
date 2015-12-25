package model.game;

import model.game.field.dynamic.Character;
import model.setting.Profile;

public class Player{
	private Profile profile;
	
	private int ID;
	private int teamID;

	private Character character;
	private int kill;
	private int death;
	private int money;
	
	public Player() {
		
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
	
}
