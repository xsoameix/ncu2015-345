package model.game.field.dynamic;

import model.game.field.FieldObject;

public class Bullet extends FieldObject {
	private int direction;
	private int playerId;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int dir) {
		this.direction = dir;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

}
