package view.play.game.field.object;

import model.game.field.dynamic.Character;
import view.base.Component;
import view.play.game.field.FieldObjectView;

public class CharacterView extends FieldObjectView{
	private Character character;

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
}
