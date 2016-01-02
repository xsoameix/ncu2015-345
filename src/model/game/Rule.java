package model.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

import model.Game;
import model.Room;
import model.game.field.FieldObject;
import model.game.field.Map;
import model.game.field.dynamic.Turf;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.game.field.map.MapBlock;

public class Rule {
	
	private Game game;
	private Map map;
	private Field field;
	private Room room;
	//private Vector<DynamicObject> List;
	
	public Rule(Game game, Map map, Field field, Room room){
		this.map = map;
		this.field = field;
		this.room = room;
		this.game = game;
	}
	
	//Call this function to get FieldObject's current mapBlock
	public MapBlock getCurrentMapBlock(FieldObject objects){
		return map.getMapBlock(objects.getLocation().x/MapBlock.getDimension().width,
						       objects.getLocation().y/MapBlock.getDimension().height);
	}
	
	//check whether tank can move or not
    //Add this current object into the mapBlocks when it can moving     
    //Remove this current object from the mapBlocks when it can moving
	//Return false when this object can't move
	public boolean MovingCheck(FieldObject current){
		FieldObject collusionObject = IsCollusion(current);
		//
		int currentPosX;
		int currentPosY;
		Rectangle rect = null;
		//
		if(collusionObject == null || (collusionObject instanceof Turf))
		{
			//get current object location in blocks
			currentPosX = current.getLocation().x/MapBlock.getDimension().width;
			currentPosY = current.getLocation().y/MapBlock.getDimension().height;
			
			for(int row = currentPosY; row <= currentPosY+1; row++){
				for(int col = currentPosX; col <= currentPosY+1; col++){
					
					//get the Rectangle of the 4 blocks which was near the current object
					rect = new Rectangle(col*MapBlock.getDimension().width,
							             row*MapBlock.getDimension().height,
							             MapBlock.getDimension().width, 
							             MapBlock.getDimension().height);
					
					//if the current object intersects with these 4 mapBlocks which near it, add itself to the mapBlock
					//otherwise, remove current object from these mapBlocks
					if(current instanceof Bullet){
						if(((Bullet)current).getRectangle().intersects(rect))
							map.getMapBlock(col, row).addDynamicObject(current);
						else
							map.getMapBlock(col, row).removeDynamicObject(current);
					}
					else{ //tank
						if(current.getRectangle().intersects(rect))
							map.getMapBlock(col, row).addDynamicObject(current);
						else
							map.getMapBlock(col, row).removeDynamicObject(current);
					}
				}
			}
			return true;
		}
		else{    //deal with bullet collusion
			if(current instanceof Bullet){
				 //Bullet hits Bullet
				if(collusionObject instanceof Bullet)
					BulletHit((Bullet)current, (Bullet)collusionObject);
				 //Bullet hits Tank
				else if(collusionObject instanceof Character)
					BulletHit((Bullet)current, (Character)collusionObject);
				 //Bullet hits Obstacle
				else if(collusionObject instanceof Obstacle)
					BulletHit((Bullet)current, (Obstacle)collusionObject);
				return false;
			}
			else //tank collusion do nothing
				return false;
		}
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
		
		for(int row = posy-1; row <= posy+1; row++){
			for(int col = posX-1; col <= posX+1; col++)
			{
				if(row!=posy && col!=posX)
					//MapBlock has nothing(just a background)
					if(! map.getMapBlock(row, col).getDynamicObjectList().isEmpty());
					{
						iter = map.getMapBlock(row, col).getDynamicObjectList().iterator();
						
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
	
	
	public void BulletHit(Bullet bullet, Character tank){
		int characterID = tank.getID();
		int bulletID = bullet.getID();
		
		//Born Location
		Point bornLocation = new Point(100,100);
		
		//Bullet hit tank, remove the bullet from the bulletList in field and mapBlock
		field.removeBullet(bullet);
		MapBlock bulletMapBlock = getCurrentMapBlock(bullet);
		bulletMapBlock.removeDynamicObject(bullet);
		
		//Bullet hit tank, remove tank from the mapBlock
		MapBlock tankMapBlock = getCurrentMapBlock(tank);
		tankMapBlock.removeDynamicObject(tank);
		
		Vector<Player> playersList = room.getPlayerList();
		Iterator<Player> iter = playersList.iterator();
		Player attacker = null;
		Player beAttacked = null;
		
		//Find the Player who fire and who was hit by the bullet
		while (iter.hasNext()){
			Player player = iter.next();
			if(player.getID() == characterID)
				beAttacked = player;
			else if(player.getID() == bulletID)
				attacker = player;
		}
		
		//beAttacked's character location set to bornLocation and add one death
		assert beAttacked!=null: "Failed to find the player who was hit by bullet in the [BulletHitTank]";
		beAttacked.getCharacter().setLocation(bornLocation);
		int newDeath = beAttacked.getDeath()+1;
		beAttacked.setDeath(newDeath);
		
		//attacker add one kill and add $200
		assert attacker!=null: "Failed to find the player who fire in the [BulletHitTank]";
		int newKill = attacker.getKill()+1;
		int newMoney = attacker.getMoney()+200;
		attacker.setKill(newKill);
		attacker.setMoney(newMoney);
		
		//call UDP to Broadcast
	}
	
	public void BulletHit(Bullet bullet, Obstacle obstacle){
		//remove the bullet from the bulletList in field and mapBlock
		field.removeBullet(bullet);
		MapBlock bulletMapBlock = getCurrentMapBlock(bullet);
		bulletMapBlock.removeDynamicObject(bullet);
		
		//remove the obstacle from the obstacleList in field and mapBlock if it is breakable
		if(obstacle.getIsBreakable() == true){
			field.removeObstacle(obstacle);
			MapBlock obstacleMapBlock = getCurrentMapBlock(obstacle);
			obstacleMapBlock.removeDynamicObject(obstacle);
		}
	}
	
	
	public void BulletHit(Bullet bullet1, Bullet bullet2){
		//remove the bullet
		field.removeBullet(bullet1);
		MapBlock bullet1MapBlock = getCurrentMapBlock(bullet1);
		bullet1MapBlock.removeDynamicObject(bullet1);
		field.removeBullet(bullet2);
		MapBlock bullet2MapBlock = getCurrentMapBlock(bullet2);
		bullet2MapBlock.removeDynamicObject(bullet2);
	}
	
	public void TankHit(Character tank){
		//Don't move
	}
	
	public void TankHit(Bullet bullet){
		//Do nothing
		//Bullet Thread deal with this event
	}
	
	public void TankHit(Obstacle obstacle){
		//Don't move
	}
	
	public void TankHit(Turf turf){
		Vector<Team> teams = game.getTeams();
		Iterator<Team> iter = teams.iterator();
		Team team = null;
		int money;
		while(iter.hasNext()){
			team = iter.next();
			if(team.getID() == turf.getTeamID()){
				money = ((Team)iter).getMoney()+100;
				((Team)iter).setMoney(money);
			}
		}
	}
	
	
	//Money Thread call this function to updata money 
	
	
	
}
