package view.play.game.field.map;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import model.game.field.map.MapBlock;
import view.base.Panel;

public class MapBlockPanel extends JComponent{
	private MapBlock mapBlock;
	private ImageIcon imageIcon;
	public MapBlockPanel(MapBlock mapBlock) {
		imageIcon=MapBlockImage.imageIcons[mapBlock.type];
	}
	public MapBlock getMapBlock() {
		return mapBlock;
	}
	public void setMapBlock(MapBlock mapBlock) {
		this.mapBlock = mapBlock;
	}

	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imageIcon.getImage(), 0, 0, null);
		g.drawString(getX()/32+", "+getY()/32, 5, 10);
	}
}
