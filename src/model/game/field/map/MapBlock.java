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
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public Vector<DynamicObject> getDynamicObjectList(){
		return objects;
	}
	
	public void addDynamicObject(DynamicObject object){
		objects.add(object);
	}
	
	public void removeDynamicObject(DynamicObject object){
		objects.remove(object);
	}
}
