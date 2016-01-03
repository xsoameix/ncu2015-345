package model;

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import model.game.Direction;
import model.game.Field;
import model.game.field.dynamic.Bullet;

public class BulletThread extends Thread {
	private Field field;

	public BulletThread(Field field) {
		this.field = field;
	}

	@Override
	public void run() {
		Bullet bulletObject = null;
		while (true) {
			Vector<Bullet> bulletList = field.getBulletList();
			Iterator<Bullet> iter = bulletList.iterator();

			synchronized (this) {
				while (iter.hasNext()) {
					bulletObject = iter.next();
					switch (bulletObject.getDirection()) {
					case Direction.UP:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x, bulletObject.getLocation().y - 25));
						break;
					case Direction.DOWN:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x, bulletObject.getLocation().y + 25));
						break;
					case Direction.LEFT:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x - 25, bulletObject.getLocation().y));
						break;
					case Direction.RIGHT:
						bulletObject.setLocation(new Point(bulletObject.getLocation().x + 25, bulletObject.getLocation().y));
						break;
					}
				}
			}
			try {
				BulletThread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
