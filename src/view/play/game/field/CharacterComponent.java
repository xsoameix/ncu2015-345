package view.play.game.field;

import model.game.Character;
import view.base.Component;

public class CharacterComponent extends Component{
	private Character character;
	
	public void move(int direction){
		character.move(direction);
		
	}
}
