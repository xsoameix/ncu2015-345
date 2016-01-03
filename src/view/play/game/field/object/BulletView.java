package view.play.game.field.object;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import model.game.field.dynamic.Bullet;
import view.play.game.field.FieldObjectView;

public class BulletView extends FieldObjectView{
	private ImageIcon image;
	public BulletView(Bullet bullet) {
		super(bullet);
		image=new ImageIcon("src/assets/img/bullet.png");
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image.getImage(), 0, 0, null);
	}
}
