package model.game.field.dynamic;

import java.awt.Point;

import model.game.field.FieldObject;

public class Obstacle extends FieldObject {
	private boolean breakable;

	public Obstacle(int ID, Point location) {
		setID(ID);
		setLocation(location);
	}

	public boolean getIsBreakable() {
		return breakable;
	}

	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}

	@Override
	public void say() {
		System.out.println("obstacle");
	}

	@Override
	public void collusion(FieldObject otherObject) {
		// TODO Auto-generated method stub
		
	}

}
