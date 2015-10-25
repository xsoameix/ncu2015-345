package model.game.field;

import model.game.Coord;

public class Map {
	private Coord size;
	private MapBlock mapBlocks[][];
	public Map(){
		
	}
	public Coord getSize() {
		return size;
	}
	public void setSize(Coord size) {
		this.size = size;
	}
	public MapBlock getMapBlock(int x, int y) {
		return mapBlocks[y][x];
	}
}
