<<<<<<< HEAD
package model.game.field;

import java.awt.Point;
import java.awt.Rectangle;

import model.game.field.map.MapBlock;

public class FieldObject {
	private Point location;
	private int ID;

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public Rectangle getRectangle(){
		Rectangle rect = new Rectangle(location.x/MapBlock.getSize().width,
				                       location.x/MapBlock.getSize().height,
				                       MapBlock.getSize().width,
				                       MapBlock.getSize().height);
		return rect;
	}

}
=======
package model.game.field;

import java.awt.Point;
import java.awt.Rectangle;

import model.game.field.map.MapBlock;

public class FieldObject {
	private Point location; //pix
	private int ID;

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public Rectangle getRectangle(){
		Rectangle rect = new Rectangle((location.x/MapBlock.getDimension().width)*MapBlock.getDimension().width,
				                       (location.x/MapBlock.getDimension().height)*MapBlock.getDimension().height,
				                       MapBlock.getDimension().width,
				                       MapBlock.getDimension().height);
		return rect;
	}

}
>>>>>>> refs/heads/model-data
