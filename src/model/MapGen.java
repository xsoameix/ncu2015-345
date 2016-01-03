package model;

import java.awt.Point;
import java.util.concurrent.atomic.AtomicInteger;

import model.game.Field;
import model.game.field.Map;
import model.game.field.dynamic.Obstacle;
import model.game.field.dynamic.Turf;
import model.game.field.map.MapBlock;

public class MapGen {
	private final int mapX = 20;
	private final int mapY = 20;
	private Field field;
	private AtomicInteger atomicInteger;

	public MapGen(AtomicInteger atomicInteger) {
		// TODO Auto-generated constructor stub
		this.atomicInteger = atomicInteger;
	}

	public Field createField(Field field, Map map) {
		this.field = field;
		field.setMap(map);
		createObstecle();
		createTurf();
		return field;
	}

	private void createTurf() {
		setTurf(1, 18);
		setTurf(10, 10);
		setTurf(18, 1);
		setTurf(2, 1);
	}

	private void setTurf(int x, int y) {
		field.addTurf(new Turf(-1, new Point(0 + x * MapBlock.getSize().width, 0 + y * MapBlock.getSize().height), atomicInteger.getAndIncrement()));
	}

	private void createObstecle() {
		for (int i = 0; i < mapX; i++) {
			setObstacle(i, 0);
		}
		for (int i = 0; i < mapX; i++) {
			setObstacle(i, 19);
		}
		for (int i = 1; i < mapX - 1; i++) {
			setObstacle(0, i);
		}
		for (int i = 1; i < mapX - 1; i++) {
			setObstacle(19, i);
		}
	}

	private void setObstacle(int x, int y) {

		field.addObstacle(new Obstacle(atomicInteger.getAndIncrement(), new Point(0 + x * MapBlock.getSize().width, 0 + y * MapBlock.getSize().height)));
	}

}
