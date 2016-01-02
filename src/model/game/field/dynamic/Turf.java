package model.game.field.dynamic;

import model.game.field.FieldObject;

public class Turf extends FieldObject {
	private int teamID = 0;

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int id) {
		this.teamID = id;
	}

}
