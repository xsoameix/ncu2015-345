package model.game.field.dynamic;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import model.game.field.FieldObject;
import model.game.field.map.MapBlock;

public class Turf extends FieldObject {
	private int teamID;
	private Point locationInBlocks;// pixel
	private int timeAtOccupy;
	private Dimension size;// pixel
	
	public Turf(){
		this.timeAtOccupy = -1;
		this.teamID = -1;
	}
	
	public int getTimeAtOccupy(){
		return timeAtOccupy;
	}
	
	public void setTimeAtOccupy(int time){
		this.timeAtOccupy = time;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int id) {
		this.teamID = id;
	}

	public Point getLocationInBlocks() {
		return locationInBlocks;
	}

	public void setLocationInBlocks(Point location) {
		this.locationInBlocks = location;
	}

	public Dimension getDimension() {
		return size;
	}

	public void setDimension(Dimension size) {
		this.size = size;
	}
	
	@Override
	public Rectangle getRectangle(){
		Rectangle rect = new Rectangle((this.getLocation().x/MapBlock.getDimension().width)*MapBlock.getDimension().width+MapBlock.getDimension().width/2,
									   (this.getLocation().x/MapBlock.getDimension().height*MapBlock.getDimension().height+MapBlock.getDimension().height/2),
				                       1,
				                       1);
		return rect;
	}
}
