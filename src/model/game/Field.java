package model.game;

import java.awt.Dimension;
import java.util.Vector;

import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.game.field.dynamic.Turf;

public class Field {
	// private int backgroundID
	private Map map;

	// put into some container?
	// private FieldObject fieldObjects[];

	private Vector<Character> characters;
	private Vector<Bullet> bullets;
	private Vector<Obstacle> obstacles;
	private Vector<Turf> turfs;

	public Field() {
		map = new Map();
		characters = new Vector<>();
		bullets = new Vector<>();
		obstacles = new Vector<>();
		turfs = new Vector<>();
	}

	public Dimension getSize() {
		return getMap().getSize();
	}

	public Map getMap() {
		return map;
	}

	public synchronized void setMap(Map map) {
		this.map = map;
	}

	public Vector<Character> getCharacterList() {
		return characters;
	}

	public Vector<Bullet> getBulletList() {
		return bullets;
	}

	public synchronized Bullet getBullet(int ID) {
		synchronized (bullets) {
			for (int i = 0; i < bullets.size(); i++) {
				if (bullets.get(i).getID() == ID) {
					return bullets.get(i);
				}
			}
			return null;
		}
	}

	public Vector<Turf> getTurfs() {
		return turfs;
	}

	public void addTurf(Turf turf) {
		// TODO Auto-generated method stub
		synchronized (turfs) {
			this.turfs.add(turf);
		}
	}

	public Vector<Obstacle> getObstacles() {
		return obstacles;
	}

	public synchronized void addCharacter(Character cha) {
		synchronized (characters) {
			this.characters.add(cha);
		}
	}

	public synchronized void removeCharacter(Character cha) {
		synchronized (characters) {
			this.characters.remove(cha);
		}
	}

	public synchronized void addBullet(Bullet bullet) {
		synchronized (bullet) {
			this.bullets.add(bullet);
		}
	}

	public synchronized void removeBullet(Bullet bullet) {
		synchronized (bullet) {
			this.bullets.remove(bullet);
		}
	}

	public synchronized void addObstacle(Obstacle obstacle) {
		synchronized (obstacles) {
			this.obstacles.addElement(obstacle);
		}
	}

	public synchronized void removeObstacle(Obstacle obstacle) {
		synchronized (obstacles) {
			this.obstacles.remove(obstacle);
		}
	}

}
