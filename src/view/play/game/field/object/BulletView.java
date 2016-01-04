package view.play.game.field.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import model.game.field.dynamic.Bullet;
import view.play.game.field.FieldObjectView;

public class BulletView extends FieldObjectView{
	private Image image;
	public BulletView(Bullet bullet) {
		super(bullet);
		image=new ImageIcon("src/assets/img/bullet.png").getImage();
		setSize(16, 16);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setLocation(getObject().getLocation());
		g.drawImage(image, 0, 0, null);
	}
}
