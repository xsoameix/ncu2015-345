package view.play.game.field.map;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class MapBlockImage {
	public static ImageIcon imageIcons[]={
			new ImageIcon("src/assets/img/mapBlock2.png"),
			new ImageIcon("src/assets/img/mapBlock2.png"),
			new ImageIcon("src/assets/img/mapBlock3.png"),
	};
	public static Image images[];
	public MapBlockImage(){
		images=new Image[3];
		for(int i=0; i<images.length; i++)
			try {
				images[i]=ImageIO.read(new File("src/assets/img/mapBlock"+(i+1)+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static Image getImage(int i){
		return images[i];
	}
}
