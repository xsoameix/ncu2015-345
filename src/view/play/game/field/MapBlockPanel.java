package view.play.game.field;

import model.game.field.MapBlock;
import view.base.Panel;

public class MapBlockPanel extends Panel{
	private MapBlock mapBlock;
	public MapBlockPanel(MapBlock mapBlock) {
		
	}
	public MapBlock getMapBlock() {
		return mapBlock;
	}
	public void setMapBlock(MapBlock mapBlock) {
		this.mapBlock = mapBlock;
	}

}
