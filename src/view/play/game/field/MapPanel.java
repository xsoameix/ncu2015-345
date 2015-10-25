package view.play.game.field;

import java.awt.Color;
import java.awt.GridLayout;

import model.game.Coord;
import model.game.field.Map;
import view.base.Panel;

public class MapPanel extends Panel{
	private Map map;
	private MapBlockPanel mapBlockPanels[][];
	
	public MapPanel() {
		setBackground(Color.WHITE);
	}
	
	private void loadData(){
		for(int y=0; y<map.getSize().height; y++)
			for(int x=0; x<map.getSize().width; x++)
				mapBlockPanels[y][x]=new MapBlockPanel(map.getMapBlock(x, y));
	}
	private void setComponents(){
		for(MapBlockPanel[] mapChipPanelsy: mapBlockPanels)
			for(MapBlockPanel mapBlockPanel: mapChipPanelsy)
				add(mapBlockPanel);		
	}
	//read the map data and draw
	public MapPanel(Map map){
		this.setMap(map);
		
		Coord size=map.getSize();
		mapBlockPanels=new MapBlockPanel[size.height][size.width];
		
		//read map data
		loadData();
		
		//grid
		setLayout(new GridLayout(size.height, size.width));
		
		//add chip
		setComponents();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}


}
