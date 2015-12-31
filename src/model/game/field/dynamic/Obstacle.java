package model.game.field.dynamic;

import model.game.field.DynamicObject;

public class Obstacle extends DynamicObject{
	private boolean breakable;
	
	public boolean getIsBreakable(){
		return breakable;
	}
	
	public void setBreakable(boolean breakable){
		this.breakable = breakable;
	}
	
}
