package model.game.field.dynamic;

import java.awt.Point;

import model.game.field.DynamicObject;

public class Character extends DynamicObject {
	private int direction;
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	
}
