package model.game.field.map;

import java.awt.Dimension;
import java.util.Vector;

import model.game.field.FieldObject;

public class MapBlock{
	private Vector<FieldObject> objects;
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
	
	public Vector<FieldObject> getDynamicObjectList(){
		return objects;
	}
	
	public void addDynamicObject(FieldObject object){
		objects.add(object);
	}
	
	public void removeDynamicObject(FieldObject object){
		objects.remove(object);
	}
}
