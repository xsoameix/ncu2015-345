package model.game.field;

import java.awt.Dimension;
import java.awt.Point;

public class Turf extends FieldObject {
	private int teamID;
	private Point location;// pixel
	private Dimension size;// pixel

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int id) {
		this.teamID = id;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Dimension getDimension() {
		return size;
	}

	public void setDimension(Dimension size) {
		this.size = size;
	}
}
