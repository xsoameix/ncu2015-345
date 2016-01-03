package view.play.game.field.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import model.game.field.map.MapBlock;

public class MapBlockPanel extends JComponent{
	private MapBlock mapBlock;
//	private ImageIcon imageIcon;
	private BufferedImage image;
	public MapBlockPanel(MapBlock mapBlock) {
//		imageIcon=MapBlockImage.imageIcons[mapBlock.type];
//		try {
////			image=ImageIO.read(new File("src/assets/img/mapBlock"+(mapBlock.type+1)+".png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
//		g.drawImage(imageIcon.getImage(), 0, 0, null);
//		g.drawImage(image, 0, 0, null);
		g.drawString(getX()/32+", "+getY()/32, 5, 10);
	}
}
