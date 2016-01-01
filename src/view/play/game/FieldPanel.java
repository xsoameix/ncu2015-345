package view.play.game;


import model.game.Field;
import view.base.extend.AbstractView;
import view.play.game.field.FieldObjectView;
import view.play.game.field.MapPanel;

public class FieldPanel extends AbstractView{
	private Field field;
	
	private MapPanel mapPanel;//show background?
	//private Dimension cameraDimension;	get from field.getCharacter?
	
	//private FieldObjectView fieldObjects[];//tank, bullet, obstacle... maybe no this variable?

	public FieldPanel(){
		setLayout(null);
		mapPanel=new MapPanel();
		add(mapPanel);
	}
	public void setField(Field field) {
		this.field=field;
	}
	public Field getField(){
		return field;
	}
}
