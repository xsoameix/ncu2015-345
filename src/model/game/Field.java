package model.game;

import java.awt.Dimension;
import java.util.Vector;

import model.game.field.DynamicObject;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;

public class Field{
	private Vector<DynamicObject> objects;
//	private int backgroundID
	private Map map;
	
	//put into some container?
	//private FieldObject fieldObjects[];
	
	private Vector<Character> characters;
	private Vector<Bullet> bullets;
	private Vector<Obstacle> obstacles;

	public Field(){
	}
	
	public Dimension getSize() {
		return getMap().getSize();
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}

	public Vector<Character> getCharacterList(){
		return characters;
	}
	
	public Vector<Bullet> getBulletList(){
		return bullets;
	}
	
	public Vector<Obstacle> getObstacleList(){
		return obstacles;
	}
	
	public void addCharacter(Character cha){
		this.characters.add(cha);
	}
	
	public void removeCharacter(Character cha){
		this.characters.remove(cha);
	}
	
	public void addBullet(Bullet bullet){
		this.bullets.add(bullet);
	}
	
	public void removeBullet(Bullet bullet){
		this.bullets.remove(bullet);
	}
	
	public void addObstacle(Obstacle obstacle){
		this.objects.addElement(obstacle);
	}
	
	public void removeObstacle(Obstacle obstacle){
		this.objects.remove(obstacle);
	}
	
	
}
