package model;

import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import model.game.Field;
import model.game.Rule;
import model.game.field.FieldObject;
import model.game.field.Map;
import model.game.field.dynamic.Turf;
import model.game.field.dynamic.Character;
import model.game.field.map.MapBlock;

public class TurfThread extends Thread{
	public Game game;
	public Field field;
	public Map map;
	private Rule rule;
	public TurfThread(Game game, Field field, Map map, Rule rule){
		this.game = game;
		this.field = field;
		this.map = map;
		this.rule = rule;
	}
	
	@Override
	public void run(){
		
		while(true){
			Vector<Turf> list = field.getTurfs();
			Iterator<Turf> iterTurf = list.iterator();
			Turf turf = null;
			
			//Check all Turf
			synchronized(this){
				while(iterTurf.hasNext()){
					turf = iterTurf.next();
					if(turf.getTeamID() != -1)
					{
						if((turf.getTimeAtOccupy()-game.getTime())>3)
						{
							rule.TankHit(turf);
						}
					}
				}
			}
			try{
				TurfThread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
		
}
