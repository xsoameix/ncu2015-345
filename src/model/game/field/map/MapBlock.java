package model.game.field.map;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

import model.game.field.dynamic.Character;
import model.game.field.dynamic.Turf;
import model.game.field.FieldObject;

public class MapBlock {
	private Point location;//in block
	private Vector<FieldObject> objects;
	private static Dimension size = new Dimension(32, 32);
	public int type;// remove!

	public MapBlock() {
	}

	public MapBlock(int type) {
		objects = new Vector<>();
		this.type = type;
	}

	public int getType() {
		objects = new Vector<>();
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Vector<FieldObject> getFieldObjects() {
		return objects;
	}

	public void addFieldObject(FieldObject object) {
		objects.add(object);
	}

	public void removeFieldObject(FieldObject object) {
		// when Tank leave MapBlock, it also remove itself from the MapBlock
		// If MapBlock has a Turf, then reset the Turf after the tank leave
//		if (object instanceof Character) {
//			Iterator<FieldObject> iter = this.objects.iterator();
//			FieldObject obj = null;
//			while (iter.hasNext()) {
//				obj = iter.next();
//				if (obj instanceof Turf) {
//					((Turf) obj).setTimeAtOccupy(-1);
//					((Turf) obj).setTeamID(-1);
//				}
//			}
//		}
		objects.remove(object);
	}

	public static Dimension getSize() {
		return size;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Rectangle getRectangle() {
		return new Rectangle(location.x, location.y, size.width, size.height);
	}

	public boolean contains(FieldObject object) {
		for(FieldObject fieldObject: objects)
			if(fieldObject.getID()==object.getID())
				return true;
		return false;
	}


}