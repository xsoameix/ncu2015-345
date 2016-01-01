package model.game.field.dynamic;

import model.game.field.FieldObject;

public class Obstacle extends FieldObject{
	private boolean breakable;
	
	public boolean getIsBreakable(){
		return breakable;
	}
	
	public void setBreakable(boolean breakable){
		this.breakable = breakable;
	}
	
}
