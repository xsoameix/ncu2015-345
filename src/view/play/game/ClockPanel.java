package view.play.game;

import java.awt.Graphics;

import view.base.Label;
import view.base.Panel;
import view.base.extend.AbstractView;

public class ClockPanel extends AbstractView{
	private Label clockLabel;
	
	public ClockPanel(){
		clockLabel=new Label("05:00");
		add(clockLabel);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		clockLabel.setText(String.valueOf(clientModel.getTime()));
	}
}
