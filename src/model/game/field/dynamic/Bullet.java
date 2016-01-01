package model.game.field.dynamic;

import model.game.field.FieldObject;

public class Bullet extends FieldObject {
	private int direction;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int dir) {
		this.direction = dir;
	}

}
