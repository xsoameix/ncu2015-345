package model.game.field.dynamic;

import java.awt.Point;
import java.awt.Rectangle;

import model.game.field.FieldObject;
import model.game.field.map.MapBlock;

public class Bullet extends FieldObject {
	private int direction = 1;
	private int playerID;

	public Bullet(int playerID, int ID, int direction,Point location) {
		// TODO Auto-generated constructor stub
		this.playerID = playerID;
		this.direction = direction;
		setLocation(new Point(location.x+16,location.y+16));
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
		Rectangle rect = new Rectangle(this.getLocation().x+10, this.getLocation().y+10,16,16);
		return rect;
	}

	@Override
	public void say() {
		System.out.println("Bullet");
	}

	@Override
	public void collusion(FieldObject otherObject) {
		// TODO Auto-generated method stub
		
	}

}
