package view.play.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import view.base.Panel;

public class MiniMapPanel extends Panel {
	private Panel panel;
	private double SCALE;
	
	public MiniMapPanel(Panel panel) {
		setPanel(panel);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(createImage(), 20, 20, null);
	}
	
	private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(panel.getPreferredSize().width, panel.getPreferredSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(SCALE, SCALE);
        AffineTransformOp scaleOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(image, null);
	}
	public void setPanel(Panel panel) {
		this.panel = panel;
	//	Dimension mapSize=fieldPanel.getField().getMap().getSize();
	//	SCALE=Math.min(getWidth()/mapSize.getWidth()*, getHeight()/mapHeight);
		SCALE=1;
	}
}
