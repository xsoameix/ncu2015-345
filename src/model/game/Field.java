package model.game;

import java.awt.Dimension;
import java.util.Vector;

import model.game.field.DynamicObject;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;

public class Field{
	private Vector<DynamicObject> objects;
//	private int backgroundID
	private Map map;
	
	//put into some container?
	//private FieldObject fieldObjects[];
	
	private Character characters[];
	private Bullet bullets[];
	private Obstacle obstacles[];
	public Field(){
	}
	public Dimension getSize() {
		return getMap().getSize();
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}

	
	
}
