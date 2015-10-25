package view.play.game;

import model.game.Coord;
import model.game.field.Field;
import view.base.Panel;
import view.play.game.field.CharacterComponent;
import view.play.game.field.MapPanel;

public class FieldPanel extends Panel{
	private Field field;
	
	private MapPanel mapPanel;
	private Coord cameraCoord;
	private CharacterComponent characters[];

	public FieldPanel(){
		mapPanel=new MapPanel();
		add(mapPanel);
	}
	public void setField(Field field) {
		this.field=field;
	}
	public Field getField(){
		return field;
	}
	public Coord getCameraCoord() {
		return cameraCoord;
	}
	public void setCameraCoord(Coord cameraCoord) {
		this.cameraCoord = cameraCoord;
	}
	public CharacterComponent[] getCharacters() {
		return characters;
	}
	public void setCharacters(CharacterComponent characters[]) {
		this.characters = characters;
	}

}
