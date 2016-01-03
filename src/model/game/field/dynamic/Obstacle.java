package model.game.field.dynamic;

import java.awt.Point;

import model.game.field.FieldObject;

public class Obstacle extends FieldObject {
	private boolean breakable = true;

	public Obstacle(int ID, Point location) {
		// TODO Auto-generated constructor stub
		setID(ID);
		setLocation(location);
	}

	public boolean getIsBreakable() {
		return breakable;
	}

	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}

}
