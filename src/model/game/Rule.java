package model.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import model.Game;
import model.Room;
import model.game.field.FieldObject;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.map.MapBlock;

public class Rule {

	private Map map;
	private Field field;
	private Game game;
	// private Vector<DynamicObject> List;

	public Rule(Game game) {
		this.field = game.getField();
		this.map = game.getField().getMap();
		this.game = game;
	}

	public boolean tankMovingCheck(FieldObject current) {
		if (IsCollusion(current) == null)
			return true;
		else
			return false;
	}

	public FieldObject IsCollusion(FieldObject current) {
		// = List.iterator();
		Iterator<FieldObject> iter;
		Rectangle CurrentRectangle = null;

		// get current object's rectangle
		// Bullet's rectangle smaller than other field object
		if (current instanceof Bullet) {
			CurrentRectangle = ((Bullet) current).getRectangle();
		} else
			CurrentRectangle = current.getRectangle();

		// MapBlocks which is nearby the current
		int posX = current.getLocation().x / MapBlock.getSize().width;
		int posy = current.getLocation().y / MapBlock.getSize().height;

		for (int i = posy - 1; i <= posy + 1; i++) {
			for (int j = posX - 1; j <= posX + 1; j++) {
				if (i != posy && j != posX)
					// MapBlock has nothing(just a background)
					if (!map.getMapBlock(i, j).getDynamicObjectList().isEmpty())
						;
				{
					iter = map.getMapBlock(i, j).getDynamicObjectList().iterator();
					while (iter.hasNext()) {
						// current crash with MapBlock's dynamicObjects
						FieldObject fieldObject = iter.next();
						// Bullet's rectangle smaller than other field object
						if (fieldObject instanceof Bullet) {
							if (((Bullet) fieldObject).getRectangle().intersects(CurrentRectangle)) {
								return (FieldObject) iter;
							}
						} else if (fieldObject.getRectangle().intersects(CurrentRectangle)) {
							return (FieldObject) iter;
						}

					}
				}
			}
		}
		return null;
	}

	public void TankHitTank() {

	}

	public void TankHitObstacle() {

	}

	public void BulletHitTank(Bullet bullet, Character character) {
		int characterID = character.getID();
		int bulletID = bullet.getID();
		// Born Location
		Point bornLocation = new Point(100, 100);
		// Bullet hit tank, bullet display
		field.removeBullet(bullet);
		Vector<Player> playersList = game.getPlayerList();
		Iterator<Player> iter = playersList.iterator();
		Player attacker = null;
		Player beAttacked = null;

		// Find the Player who fire and who was hit by the bullet
		while (iter.hasNext()) {
			Player player = iter.next();
			if (player.getID() == characterID)
				beAttacked = player;
			else if (player.getID() == bulletID)
				attacker = player;
		}

		// beAttacked's character location set to bornLocation and add one death
		assert beAttacked != null : "Failed to find the player who was hit by bullet in the [BulletHitTank]";
		beAttacked.getCharacter().setLocation(bornLocation);
		int newDeath = beAttacked.getDeath() + 1;
		beAttacked.setDeath(newDeath);

		// attacker add one kill and add $200
		assert attacker != null : "Failed to find the player who fire in the [BulletHitTank]";
		int newKill = attacker.getKill() + 1;
		int newMoney = attacker.getMoney() + 200;
		attacker.setKill(newKill);
		attacker.setMoney(newMoney);

		// call UDP to boardcast
	}

	public void BulletHitObstacle() {

	}

	public void BulletHitBullet() {

	}

}
