package view.play.game.field.object;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.game.field.dynamic.Character;
import view.play.game.field.FieldObjectView;

public class CharacterView extends FieldObjectView{
	private Image images[];
	
	public CharacterView(Character character) {
		super(character);
		images=new Image[4];
		for(int i=0; i<images.length; i++)
			images[i]=new ImageIcon("src/assets/img/tank/"+i+".jpg").getImage();
	}
	@Override
	public Character getObject() {
		return (Character) super.getObject();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(images[getObject().getDirection()], 0, 0, null);
	}
}
