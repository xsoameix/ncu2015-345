package view.play.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import view.base.Label;
import view.base.Panel;

public class MiniMapPanel extends Panel {
	private FieldPanel fieldPanel;
	private double SCALE;
	private Label label;
	private ImageIcon image;
	public MiniMapPanel(FieldPanel component) {
		setMap(component);
		
		image=new ImageIcon();
		
		label=new Label();
//		setLayout(new GridLayout(0, 1));
		label.setSize(200, 200);
		label.setLocation(0, 0);
		label.setPreferredSize(new Dimension(200, 200));
		label.setIcon(image);
		add(label);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(createImage(), 0, 0, null);
		//		image.setImage(createImage());
	}
	
	private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(fieldPanel.getSize().width, fieldPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        fieldPanel.paint(g2d);
        g2d.dispose();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(SCALE, SCALE);
        AffineTransformOp scaleOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(image, null);
	}
	public void setMap(FieldPanel fieldPanel) {
		this.fieldPanel = fieldPanel;
//		Dimension mapSize=fieldPanel.getField().getMap().getSize();
//		SCALE=Math.min(getWidth()/mapSize.getWidth()*, getHeight()/mapHeight);
	}
}
