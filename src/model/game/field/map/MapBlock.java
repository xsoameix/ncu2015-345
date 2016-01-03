package model.game.field.map;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.Vector;
import model.game.field.dynamic.Character;
import model.game.field.FieldObject;
import model.game.field.dynamic.Turf;

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
		//when Tank leave MapBlock, it also remove itself from the MapBlock
		//If MapBlock has a Turf, then reset the Turf after the tank leave
		if(object instanceof Character){
			Iterator<FieldObject> iter = this.objects.iterator();
			FieldObject obj = null;
			while(iter.hasNext()){
				obj = iter.next();
				if(obj instanceof Turf)
				{
					((Turf) obj).setTimeAtOccupy(-1); 
					((Turf) obj).setTeamID(-1);
				}
			}
		}
		objects.remove(object);
	}
	
	public static Dimension getDimension(){
		return size;
	}
}
