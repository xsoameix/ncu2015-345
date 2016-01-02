package model.game.field.dynamic;

import java.awt.Dimension;
import java.awt.Point;

import model.game.field.FieldObject;

public class Turf extends FieldObject {
	private int teamID;
	private Point locationInBlocks;// pixel
	private Dimension size;// pixel

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
}
