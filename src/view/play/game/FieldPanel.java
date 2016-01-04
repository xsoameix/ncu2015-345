package view.play.game;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ImageIcon;

import model.game.Field;
import model.game.Player;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.game.field.map.MapBlock;
import view.base.Panel;
import view.base.extend.AbstractView;
import view.play.game.field.MapPanel;

public class FieldPanel extends AbstractView{
	private MapPanel mapPanel;//show background?
	public FieldPanel(){
		setLayout(null);
		mapPanel=new MapPanel();
		add(mapPanel);
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
//		pane.add(new ObstacleView(obstacle), new Integer(100));
	}
	public void removeObstacle(Obstacle obstacle){
//		for(int i=0; i<pane.getComponentCount(); i++)
//			if(((FieldObjectView)getComponent(i)).getObject().equals(obstacle)){
//				remove(getComponent(i));
//				break;
//			}
	}
	public void addCharacter(Character character){
//		pane.add(new CharacterView(character), new Integer(100));
	}
	public void removeCharacter(Character character){
//		for(int i=0; i<pane.getComponentCount(); i++)
//			if(((FieldObjectView)getComponent(i)).getObject().equals(character)){
//				remove(getComponent(i));
//				break;
//			}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Character character=clientModel.getIndividual().getCharacter();
		mapPanel.setLocation(-character.getLocation().x+500, -character.getLocation().y+250);
	}
	public void setup(){
		Dimension size=clientModel.getGame().getField().getMap().getSize();
		mapPanel.setSize((size.width+1)*MapBlock.getSize().width, (size.height+1)*MapBlock.getSize().height);
	}

	public Panel getMapPanel() {
		return mapPanel;
	}
}
