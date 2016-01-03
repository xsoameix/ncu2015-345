package model.game.field.dynamic;

import java.awt.Rectangle;

import model.game.field.FieldObject;
import model.game.field.map.MapBlock;

public class Bullet extends FieldObject {
	private int direction;
	private int playerID;

	public Bullet(int playerID, int ID) {
		// TODO Auto-generated constructor stub
		this.playerID = playerID;
		setID(ID);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int dir) {
		this.direction = dir;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int id) {
		this.playerID = id;
	}

	@Override
	public Rectangle getRectangle() {
		Rectangle rect = new Rectangle((this.getLocation().x / MapBlock.getSize().width) * MapBlock.getSize().width, (this.getLocation().x / MapBlock.getSize().height) * MapBlock.getSize().height, 30, 10);
		return rect;
	}

}
