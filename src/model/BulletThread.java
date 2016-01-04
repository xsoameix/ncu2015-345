package model;

import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import model.game.Direction;
import model.game.Field;
import model.game.Rule;
import model.game.field.dynamic.Bullet;

public class BulletThread extends Thread {
	private Field field;
	private Rule rule;
	private ServerModel serverModel;

	public BulletThread(ServerModel serverModel) {
		this.serverModel = serverModel;
		this.field = serverModel.getGame().getField();
		rule = serverModel.getGame().getRule();
		
		moving=new Point[]{
			new Point(0, moveUnit),
			new Point(0, -moveUnit),
			new Point(-moveUnit, 0),
			new Point(moveUnit, 0),
		};
	}
	private int moveUnit=8;
	private Point moving[];
	@Override
	public void run() {
		Bullet bulletObject = null;
		while (true) {
			Vector<Bullet> bulletList = field.getBulletList();
			Iterator<Bullet> iter = bulletList.iterator();
			synchronized (this) {

			
				for (int i = 0; i < bulletList.size(); i++) {
					bulletObject = bulletList.get(i);
//					System.out.println("[BulletThreaD] run bulletObject Direction : " + bulletObject.getDirection());
//					System.out.println("[BulletThreaD] run bulletObject LOCATION : " + bulletObject.getLocation());
					
//					Point point=new Point(bulletObject.getLocation());
//					point.translate(moving[bulletObject.getDirection()-1].x, moving[bulletObject.getDirection()-1].y);
					bulletObject.getLocation().translate(moving[bulletObject.getDirection()-1].x, moving[bulletObject.getDirection()-1].y);

//					boolean move = rule.MovingCheck(bulletObject);
//					serverModel.setLocation(bulletObject, point);
					boolean move=true;
//					System.out.println("[BulletThreaD] run bulletObject movecheck : " + move);
					try {
						if (move) {
							serverModel.updateBullet(bulletObject);
						}else{
							serverModel.removeBullet(bulletObject);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// while (iter.hasNext()) {
				// bulletObject = iter.next();
				// switch (bulletObject.getDirection()) {
				// case Direction.UP:
				// bulletObject.setLocation(new
				// Point(bulletObject.getLocation().x,
				// bulletObject.getLocation().y - 25));
				// break;
				// case Direction.DOWN:
				// bulletObject.setLocation(new
				// Point(bulletObject.getLocation().x,
				// bulletObject.getLocation().y + 25));
				// break;
				// case Direction.LEFT:
				// bulletObject.setLocation(new
				// Point(bulletObject.getLocation().x - 25,
				// bulletObject.getLocation().y));
				// break;
				// case Direction.RIGHT:
				// bulletObject.setLocation(new
				// Point(bulletObject.getLocation().x + 25,
				// bulletObject.getLocation().y));
				// break;
				// }
				// try {
				// serverModel.updateBullet(bulletObject);
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// rule.MovingCheck(bulletObject);
				// }
			}
			try {
				BulletThread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
