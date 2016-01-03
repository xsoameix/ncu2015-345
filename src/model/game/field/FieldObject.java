package model.game.field;

import java.awt.Point;
import java.awt.Rectangle;

import model.game.field.map.MapBlock;

public class FieldObject {
	private Point location;
	private int ID;

	public FieldObject() {
		// TODO Auto-generated constructor stub
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
		Rectangle rect = new Rectangle(location.x / MapBlock.getSize().width,
				location.x / MapBlock.getSize().height,
				MapBlock.getSize().width, MapBlock.getSize().height);
		return rect;
	}

}