package model;

import java.util.Vector;

import model.game.Field;
import model.game.Player;
import model.game.Rule;
import model.game.Team;
import model.game.field.Map;

public class Game {
	private Vector<Team> teams;
	private int time;
	private Field field;

	private Rule rule;

	public Game() {
		field = new Field();
	}

	public void setMap(Map map) {
		field.setMap(map);
	}

	public Vector<Team> getTeams() {
		return teams;
	}

	public void addTeam(Team team) {
		teams.add(team);
	}

	public void remove(Team team) {
		teams.remove(team);
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Field getField() {
		return field;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
