package view.play.game.field;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.ClientModel;
import model.game.Player;
import model.game.field.Map;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import model.game.field.dynamic.Turf;
import model.game.field.map.MapBlock;
import view.base.extend.AbstractView;

public class MapPanel extends AbstractView{
	private Map map;
	
	private ImageIcon tankImages[];
	private Image mapBlockImages;
	private Image bulletImage;
	private ImageIcon turfImages[];
	private Image obstacleImage;
	public MapPanel() {
		tankImages=new ImageIcon[5];
		for(int i=0; i<tankImages.length; i++)
			tankImages[i]=new ImageIcon("src/assets/img/tank"+i+".png");

		try {
			bulletImage=ImageIO.read(new File("src/assets/img/bullet.png"));
			obstacleImage=ImageIO.read(new File("src/assets/img/mapBlock2.png"));
			mapBlockImages=ImageIO.read(new File("src/assets/img/mapBlock1.png"));
			turfImages=new ImageIcon[3];
			for(int i=0; i<turfImages.length; i++)
				turfImages[i]=new ImageIcon("scr/assets/img/turf"+i+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int y=0; y<map.getSize().height; y++){
			for(int x=0; x<map.getSize().width; x++){
				g.drawImage(mapBlockImages, x*MapBlock.getSize().width, y*MapBlock.getSize().height, null);
			}
		}
		for(Turf turf: clientModel.getGame().getField().getTurfs()){
//			g.drawImage(turfImages[turf.getTeamID()+1].getImage(), turf.getLocation().x, turf.getLocation().y, null);
			g.setColor(turf.getTeamID()==1?Color.BLUE:turf.getTeamID()==2?Color.RED: Color.GRAY);
			g.fillRect(turf.getLocation().x, turf.getLocation().y, MapBlock.getSize().width, MapBlock.getSize().height);
		}
		for(Bullet bullet: clientModel.getGame().getField().getBulletList()){
			g.drawImage(bulletImage, bullet.getLocation().x, bullet.getLocation().y, null);
//			g.drawRect(bullet.getLocation().x, bullet.getLocation().y, MapBlock.getSize().width/2, MapBlock.getSize().height/2);
		}
		for(Player player: clientModel.getRoom().getPlayerList()){
			Character character=player.getCharacter();
			g.drawImage(tankImages[character.getDirection()].getImage(), character.getLocation().x, character.getLocation().y, null);
//			g.drawRect(character.getLocation().x, character.getLocation().y, MapBlock.getSize().width, MapBlock.getSize().height);
		}
		for(Obstacle obstacle: clientModel.getGame().getField().getObstacles()){
			g.drawImage(obstacleImage, obstacle.getLocation().x, obstacle.getLocation().y, null);
//			g.drawRect(obstacle.getLocation().x, obstacle.getLocation().y, MapBlock.getSize().width, MapBlock.getSize().height);
		}

	}
	@Override
	public void setModel(ClientModel clientModel) {
		super.setModel(clientModel);
		map=clientModel.getGame().getField().getMap();
	}

}
