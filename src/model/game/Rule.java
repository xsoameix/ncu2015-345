package model.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import model.Room;
import model.game.field.FieldObject;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.map.MapBlock;

public class Rule {
	
	private Map map;
	private Field field;
	private Room room;
	//private Vector<DynamicObject> List;
	
	public Rule(Map map, Field field, Room room){
		this.map = map;
		this.field = field;
		this.room = room;
	}
	
	public boolean tankMovingCheck(FieldObject current){
		if(IsCollusion(current) == null)
			return true;
		else
			return false;
	}
	
	public FieldObject IsCollusion(FieldObject current){
		// = List.iterator();
		Iterator<FieldObject> iter;
		Rectangle CurrentRectangle = null;
		
		//get current object's rectangle
		//Bullet's rectangle smaller than other field object
		if(current instanceof Bullet){
			CurrentRectangle = ((Bullet)current).getRectangle();
		}
		else
			CurrentRectangle = current.getRectangle();
		
		//MapBlocks which is nearby the current 
		int posX = current.getLocation().x/MapBlock.getDimension().width;
		int posy = current.getLocation().y/MapBlock.getDimension().height;
		
		for(int i = posy-1; i <= posy+1; i++){
			for(int j = posX-1; j <= posX+1; j++)
			{
				if(i!=posy && j!=posX)
					//MapBlock has nothing(just a background)
					if(! map.getMapBlock(i, j).getDynamicObjectList().isEmpty());
					{
						iter = map.getMapBlock(i, j).getDynamicObjectList().iterator();
						while( iter.hasNext()){
							//current crash with MapBlock's dynamicObjects
							FieldObject fieldObject = iter.next();
							//Bullet's rectangle smaller than other field object
							if(fieldObject instanceof Bullet){
								if(((Bullet)fieldObject).getRectangle().intersects(CurrentRectangle)){
									return (FieldObject)iter;
								}
							}
							else if(fieldObject.getRectangle().intersects(CurrentRectangle)){
								return (FieldObject)iter;
							}
								
						}
					}
			}
		}
		return null;
	}
	
	public void TankHitTank(){
		
	}
	
	public void TankHitObstacle(){
		
	}
	
	public void BulletHitTank(Bullet bullet, Character character){
	
	}
	
	public void BulletHitObstacle(){
		
	}
	
	public void BulletHitBullet(){
		
	}
	
	
}
