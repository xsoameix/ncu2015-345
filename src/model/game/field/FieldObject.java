package model.game.field;

import java.awt.Point;

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

}
