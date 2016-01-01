package view.play.game.field;

import model.game.field.DynamicObject;
import view.base.Component;

public class FieldObjectView extends Component{
	private DynamicObject object;

	public FieldObjectView(DynamicObject object){
		setObject(object);
	}
	
	public DynamicObject getObject() {
		return object;
	}
	public void setObject(DynamicObject object) {
		this.object = object;
	}
	
	
}
