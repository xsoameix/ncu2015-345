package model;

import java.util.Vector;

import model.game.Field;
import model.game.Player;
import model.game.Rule;
import model.game.Team;
import model.game.field.Map;
import model.game.field.dynamic.Turf;

public class Game {
	private Vector<Team> teams;
	private int time;
	private Field field;
	private Rule rule;

	public Game() {
		field = new Field();
		teams = new Vector<Team>();
		teams.add(new Team(1));
		teams.add(new Team(2));
		rule = new Rule();
	}

	public void setMap(Map map) {
		field.setMap(map);
	}

	public Vector<Team> getTeams() {
		return teams;
	}

	public synchronized Team getTeam(int ID) {
		synchronized (teams) {
			for (int i = 0; i < teams.size(); i++) {
				if (teams.get(i).getID() == ID) {
					return teams.get(i);
				}
			}
			return null;
		}
	}

	public synchronized Player getPlayer(int ID) {
		synchronized (teams) {
			for (int i = 0; i < teams.size(); i++) {
				if (teams.get(i).getPlayer(ID) != null) {
					return teams.get(i).getPlayer(ID);
				}
			}
			return null;
		}
	}

	public synchronized void addTeam(Team team) {
		synchronized (teams) {
			teams.add(team);
		}
	}

	public synchronized void remove(Team team) {
		synchronized (teams) {
			teams.remove(team);
		}
	}

	public synchronized void setField(Field field) {
		this.field = field;
	}

	public Field getField() {
		return field;
	}

	public Rule getRule() {
		return rule;
	}

	public synchronized void setRule(Rule rule) {
		this.rule = rule;
	}

	public int getTime() {
		return time;
	}

	public synchronized void setTime(int time) {
		this.time = time;
	}

	public synchronized Turf getTurf(int id) {
		synchronized (field.getTurfs()) {
			for (int i = 0; i < field.getTurfs().size(); i++) {
				if (field.getTurfs().get(i).getID() == id) {
					return field.getTurfs().get(i);
				}
			}
			return null;
		}
	}
}
