package view.play.game;

import java.awt.Graphics;

import view.base.Label;
import view.base.Panel;

public class ClockPanel extends Panel{
	private Integer time=100;
	private Label clockLabel;
	
	public ClockPanel(){
		clockLabel=new Label("05:00");
		add(clockLabel);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		clockLabel.setText(String.valueOf(time));
	}
}
