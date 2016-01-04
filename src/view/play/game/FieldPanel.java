package view.play.game;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLayeredPane;

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
	
	
	private JLayeredPane pane;
	private MapPanel mapPanel;//show background?
	
	//private FieldObjectView fieldObjects[];//tank, bullet, obstacle... maybe no this variable?

	public FieldPanel(){
		setLayout(new GridLayout());
		pane=new JLayeredPane();
		add(pane);
		
		mapPanel=new MapPanel();
		pane.add(mapPanel, new Integer(0));
		
//		bulletMap=new HashMap<Integer, Bullet>();
		
		
	}
	public void setField(Field field) {
		this.field=field;
	}
	public Field getField(){
		return field;
	}
	
//	private HashMap<Integer, Bullet> bulletMap;
	
	//API
	public void addBullet(Bullet bullet){
//		if(!bulletMap.containsKey(bullet.getID())){
//			pane.add(new BulletView(bullet), new Integer(101));
//			bulletMap.put(bullet.getID(), bullet);
//		}
	}
	public void removeBullet(Bullet bullet){
//		for(Component component: pane.getComponents()){
//			if(component instanceof FieldObjectView){
//				pane.remove(component);
//				break;
//			}
//		}
//		for(int i=0; i<pane.getComponentCount(); i++)
//			if(getComponent(i) instanceof FieldObjectView)
//				if(((FieldObjectView)getComponent(i)).getObject().equals(bullet)){
//					remove(getComponent(i));
//					break;
//				}
	}
	public void addObstacle(Obstacle obstacle){
		pane.add(new ObstacleView(obstacle), new Integer(100));
	}
	public void removeObstacle(Obstacle obstacle){
		for(int i=0; i<pane.getComponentCount(); i++)
			if(((FieldObjectView)getComponent(i)).getObject().equals(obstacle)){
				remove(getComponent(i));
				break;
			}
	}
	public void addCharacter(Character character){
		pane.add(new CharacterView(character), new Integer(100));
	}
	public void removeCharacter(Character character){
		for(int i=0; i<pane.getComponentCount(); i++)
			if(((FieldObjectView)getComponent(i)).getObject().equals(character)){
				remove(getComponent(i));
				break;
			}
	}
//	private Image bulletImage;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(Bullet bullet: clientModel.getGame().getField().getBulletList()){
			g.drawRect(bullet.getLocation().x, bullet.getLocation().y, 16, 16);
		}
	}
	public void setObstacles(Vector<Obstacle> obstacles) {
		for(Obstacle obstacle: obstacles){
			addObstacle(obstacle);
		}
	}
}
