package model;

import model.game.Player;
import model.game.field.Field;

public class Game {
	private Player players[];
	private Field field;
	private Result result;
	
	public Field getField() {
		return field;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player players[]) {
		this.players = players;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	
}
