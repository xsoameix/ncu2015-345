package view.play.game.field;

import java.awt.Dimension;
import java.awt.GridLayout;

import model.game.field.Map;
import view.base.Panel;
import view.play.game.field.map.MapBlockPanel;

public class MapPanel extends Panel{
	private Map map;
	private MapBlockPanel mapBlockPanels[][];
	private Dimension blockSize=new Dimension(32, 32);
	
	
	public MapPanel() {
		this(new Map());
	}
	public MapPanel(Map map){
		this.setMap(map);
		
		Dimension size=map.getSize();
		
		setSize(new Dimension(blockSize.width*size.width, blockSize.height*size.height));
		setLayout(new GridLayout(size.height, size.width));
		mapBlockPanels=new MapBlockPanel[size.height][size.width];
		
		loadData();
		setComponents();
	}
	private void loadData(){
		for(int y=0; y<map.getSize().height; y++)
			for(int x=0; x<map.getSize().width; x++){
				mapBlockPanels[y][x]=new MapBlockPanel(map.getMapBlock(x, y));
//				mapBlockPanels[y][x]=new MapBlockPanel(new MapBlock());
//				mapBlockPanels[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
	}
	private void setComponents(){
		for(MapBlockPanel[] mapChipPanelsy: mapBlockPanels)
			for(MapBlockPanel mapBlockPanel: mapChipPanelsy)
				add(mapBlockPanel);		
	}
	//read the map data and draw


	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}


}
