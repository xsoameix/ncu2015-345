package model.game.field.dynamic;

import model.game.field.FieldObject;

public class Character extends FieldObject {
	private int direction;
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	
}
