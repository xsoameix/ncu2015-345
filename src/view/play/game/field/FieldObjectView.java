package view.play.game.field;

import model.game.field.FieldObject;
import view.base.Component;

public class FieldObjectView extends Component{
	private FieldObject object;

	public FieldObjectView(FieldObject object){
		setObject(object);
	}
	
	public FieldObject getObject() {
		return object;
	}
	public void setObject(FieldObject object) {
		this.object = object;
	}
	
	
}
