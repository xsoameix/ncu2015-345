package model.game.field.dynamic;

import java.awt.Point;

import model.game.Direction;
import model.game.field.FieldObject;

public class Character extends FieldObject {
	private int direction;
	private int playerID;

	public Character(int playerID) {
		// TODO Auto-generated constructor stub
		this.playerID = playerID;
	}

	public void setLocation(Point location) {
		int oldX = getLocation().x, oldY = getLocation().y;
		int newX = location.x, newY = location.y;
		int resultX = newX - oldX, resultY = newY - oldY;
		assert (resultX != 0 && resultY == 0) || (resultX == 0 && resultY != 0) : "[Character] setLocation result location error : resultX " + resultX + " resultY " + resultY;
		if (resultX > 0) {
			setDirection(Direction.RIGHT);
		} else if (resultY > 0) {
			setDirection(Direction.UP);
		} else if (resultX < 0) {
			setDirection(Direction.LEFT);
		} else if (resultY < 0) {
			setDirection(Direction.DOWN);
		}
		super.setLocation(location);

	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerId) {
		this.playerID = playerId;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}