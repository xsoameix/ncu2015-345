package view.play.game.field.object;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.game.field.FieldObject;
import model.game.field.dynamic.Turf;
import view.play.game.field.FieldObjectView;;

public class TurfView extends FieldObjectView {
	private Image images[];
	
	public TurfView(FieldObject object) {
		super(object);
		
		images=new Image[3];
		for(int i=0; i<images.length; i++)
			images[i]=new ImageIcon("src/assets/img/turf"+i+".jpg").getImage();
	}

	@Override
	public Turf getObject() {
		return (Turf) super.getObject();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(images[getObject().getTeamID()], 0, 0, null);
	}
}
