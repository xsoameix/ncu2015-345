package model;

import java.util.Vector;

import model.game.Field;
import model.game.Player;
import model.game.Rule;
import model.game.Team;
import model.game.field.Map;

public class Game{
	private Vector<Team> teams;
	private int time;
	private Field field;
	
	private Rule rule;	

	public Game(){
		field=new Field();
	}
	
	public void setMap(Map map){
		field.setMap(map);
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
	
}
