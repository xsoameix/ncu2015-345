package model.game.field.dynamic;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import model.game.field.FieldObject;
import model.game.field.map.MapBlock;

public class Turf extends FieldObject {
	private int teamID = -1;
	private Point locationInBlocks;// pixel
	private int timeAtOccupy = -1;
	private Dimension size;// pixel

	public Turf(int teamID, Point location, int ID) {
		this.teamID = teamID;
		setID(ID);
		setLocation(location);
	}

	public int getTimeAtOccupy() {
		return timeAtOccupy;
	}

	public void setTimeAtOccupy(int time) {
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

	public Dimension getSize() {
		return size;
	}

	public void getSize(Dimension size) {
		this.size = size;
	}

	@Override
	public Rectangle getRectangle() {
		Rectangle rect = new Rectangle((this.getLocation().x / MapBlock.getSize().width) * MapBlock.getSize().width + MapBlock.getSize().width / 2, (this.getLocation().x / MapBlock.getSize().height * MapBlock.getSize().height + MapBlock.getSize().height / 2), 1, 1);
		return rect;
	}
	
	

	@Override
	public void say() {
		System.out.println("Turf");
	}

	@Override
	public void collusion(FieldObject otherObject) {
		// TODO Auto-generated method stub
		
	}

}