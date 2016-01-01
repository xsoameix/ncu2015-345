package view.play.game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import view.base.Panel;

public class MiniMapPanel extends Panel {
	private Component dummy;
	private double SCALE;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(createImage(), 0, 0, null);
	}
	
	private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(dummy.getPreferredSize().width, dummy.getPreferredSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        dummy.paint(g2d);
        g2d.dispose();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(SCALE, SCALE);
        AffineTransformOp scaleOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(image, null);
	}
	public void setDummy(Component dummy) {
		this.dummy = dummy;
		SCALE=getPreferredSize().getWidth()/dummy.getPreferredSize().getWidth();
	}
}
