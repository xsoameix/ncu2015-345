package model.game.field;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import model.game.field.map.MapBlock;

public abstract class FieldObject {
	private Point location;
	private Dimension size;
	private int ID;
	

	public FieldObject() {
		location = new Point(0, 0);
	}

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

	public Rectangle getRectangle() {
		// Rectangle rect = new Rectangle(location.x / MapBlock.getSize().width,
		// location.x / MapBlock.getSize().height, MapBlock.getSize().width,
		// MapBlock.getSize().height);
		Rectangle rect = new Rectangle(location.x, location.y, MapBlock.getSize().width, MapBlock.getSize().height);
		return rect;
	}

	abstract public void say();

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
}