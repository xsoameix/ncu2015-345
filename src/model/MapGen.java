package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import model.game.Field;
import model.game.field.Map;
import model.game.field.dynamic.Obstacle;
import model.game.field.dynamic.Turf;
import model.game.field.map.MapBlock;

public class MapGen {
	private Dimension size;//in block
	private Field field;
	private AtomicInteger atomicInteger;

	public MapGen(AtomicInteger atomicInteger, Dimension size) {
		this.atomicInteger = atomicInteger;
		this.size = size;
	}

	public Field createField(Field field, Map map) {
		this.field = field;
		field.setMap(map);
		createObstecle();
		createTurf();
		return field;
	}

	private void createTurf() {
		setTurf(1, size.height-2);
		setTurf(size.width/2, size.height/2);
		setTurf(size.width/2, 1);
		setTurf(1, 1);
	}

	private void setTurf(int x, int y) {
		field.addTurf(new Turf(-1, new Point(0 + x * MapBlock.getSize().width, 0 + y * MapBlock.getSize().height), atomicInteger.getAndIncrement()));
	}

	private void createObstecle() {
		for (int i = 0; i < size.width; i++) {
			setObstacle(i, 0, false);
		}
		for (int i = 0; i < size.width; i++) {
			setObstacle(i, size.height-1, false);
		}
		for (int i = 1; i < size.height - 1; i++) {
			setObstacle(0, i, false);
		}
		for (int i = 1; i < size.height - 1; i++) {
			setObstacle(size.width-1, i, false);
		}
		
		for(int i=0; i<200; i++){
			int x=new Random().nextInt(size.width);
			int y=new Random().nextInt(size.height);
			while((x<5&&y<5)||(x>size.width-5&&y>size.height-5)){
				x=new Random().nextInt(size.width);
				y=new Random().nextInt(size.height);
			}
			setObstacle(new Random().nextInt(size.width), new Random().nextInt(size.height), true);
		}
	}

	private void setObstacle(int x, int y, boolean breakable) {
		Obstacle obstacle=new Obstacle(atomicInteger.getAndIncrement(), new Point(0 + x * MapBlock.getSize().width, 0 + y * MapBlock.getSize().height));
		obstacle.setBreakable(breakable);
		field.addObstacle(obstacle);
	}

}
