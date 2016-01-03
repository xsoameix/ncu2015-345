package model.game.field;

import java.awt.Dimension;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.game.field.map.MapBlock;

public class Map {
	private Dimension size;
	private MapBlock mapBlocks[][];// [y][x]
	// private static final Maps maps=new Maps();

	public Map() {
		// default map
		 setSize(new Dimension(50,20));
		 mapBlocks=new MapBlock[20][50];
		 for(int y=0; y<size.height; y++)
		 for(int x=0; x<size.width; x++)
		 mapBlocks[y][x]=new MapBlock();
	}

	public Map(Dimension size, MapBlock mapBlocks[][]) {
		this.size = size;
		this.mapBlocks = mapBlocks;
	}

	public Map(String fileName) {
		mapBlocks = new MapBlock[size.height][size.width];

		JsonObject json = new JsonObject();
		Gson gson = new Gson();

		size = gson.fromJson(json.get("size"), Dimension.class);

		json.get("scene");
		json.get("obstacle");
		json.get("respond");
		json.get("turf");
	}

	public Dimension getSize() {
		return size;
	}

	public void setDimension(Dimension size) {
		this.size = size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public MapBlock getMapBlock(int x, int y) {
		return mapBlocks[y][x];
	}
}
