package view.play.game.field.object;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import model.game.field.dynamic.Character;
import model.game.field.map.MapBlock;
import view.play.game.field.FieldObjectView;

public class CharacterView extends FieldObjectView{
	private ImageIcon images[];
	
	public CharacterView(Character character) {
		super(character);
		setLocation(character.getLocation());
		setSize(MapBlock.getSize());
		
		images=new ImageIcon[5];
		for(int i=0; i<images.length; i++)
			images[i]=new ImageIcon("src/assets/img/tank"+i+".png");
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	@Override
	public Character getObject() {
		return (Character) super.getObject();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		System.out.println(getObject().getDirection());
		setLocation(getObject().getLocation());
		g.drawImage(images[getObject().getDirection()].getImage(), 0, 0, null);
	}
}
