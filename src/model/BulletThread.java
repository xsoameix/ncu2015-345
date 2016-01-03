package model;

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import model.game.Field;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.map.MapBlock;

public class BulletThread extends Thread{
	private Field field;
	
	public BulletThread(Field field){
		this.field = field;
	}
	
	@Override
	public void run(){
		
		Bullet bulletObject = null;
		
		while(true){
			Vector<Bullet> bulletList = field.getBulletList();
			Iterator<Bullet> iter = bulletList.iterator();
			
			synchronized(this){
				while(iter.hasNext()){
					bulletObject = iter.next();
					switch(bulletObject.getDirection()){
					case 1:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x, 
								                           bulletObject.getLocation().y-25));
						break;	
					case 2:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x, 
		                        bulletObject.getLocation().y+25));
						break;	
					case 3:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x-25, 
		                        bulletObject.getLocation().y));
						break;	
					case 4:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x+25, 
		                        bulletObject.getLocation().y));
						break;
					}
				}
			}
			try{
				BulletThread.sleep(50);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		
		
			
	}
}
