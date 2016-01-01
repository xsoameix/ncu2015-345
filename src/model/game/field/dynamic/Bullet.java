package model.game.field.dynamic;

import model.game.field.DynamicObject;

public class Bullet extends DynamicObject{
	private int direction;
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int dir){
		this.direction = dir;
	}

}
