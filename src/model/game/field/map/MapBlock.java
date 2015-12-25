package model.game.field.map;

import java.awt.Dimension;
import java.util.Vector;

import model.game.field.DynamicObject;

public class MapBlock{
	private Vector<DynamicObject> objects;
	private static Dimension size;
	
	public int type;//remove!
	public MapBlock(){
		
	}
	public MapBlock(int type) {
		this.type=type;
	}
}
