package view.play.game;


import model.game.Field;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import view.base.extend.AbstractView;
import view.play.game.field.FieldObjectView;
import view.play.game.field.MapPanel;
import view.play.game.field.object.BulletView;
import view.play.game.field.object.CharacterView;
import view.play.game.field.object.ObstacleView;

public class FieldPanel extends AbstractView{
	private Field field;
	
	private MapPanel mapPanel;//show background?
	
	//private FieldObjectView fieldObjects[];//tank, bullet, obstacle... maybe no this variable?

	public FieldPanel(){
		setLayout(null);
		mapPanel=new MapPanel();
		add(mapPanel, 0);
	}
	public void setField(Field field) {
		this.field=field;
	}
	public Field getField(){
		return field;
	}
	
	
	//API
	public void addBullet(Bullet bullet){
		add(new BulletView(bullet));
	}
	public void removeBullet(Bullet bullet){
		for(int i=1; i<getComponentCount(); i++)
			if(((FieldObjectView)getComponent(i)).getObject().equals(bullet)){
				remove(getComponent(i));
				break;
			}
	}
	public void addObstacle(Obstacle obstacle){
		add(new ObstacleView(obstacle));
	}
	public void removeObstacle(Obstacle obstacle){
		for(int i=1; i<getComponentCount(); i++)
			if(((FieldObjectView)getComponent(i)).getObject().equals(obstacle)){
				remove(getComponent(i));
				break;
			}
	}
	public void addCharacter(Character character){
		add(new CharacterView(character));
	}
	public void removeCharacter(Character character){
		for(int i=1; i<getComponentCount(); i++)
			if(((FieldObjectView)getComponent(i)).getObject().equals(character)){
				remove(getComponent(i));
				break;
			}
	}
}
