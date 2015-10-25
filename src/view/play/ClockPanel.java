package view.play;

import view.base.Label;
import view.base.Panel;

public class ClockPanel extends Panel{
	private Label clockLabel;
	
	public ClockPanel(){
		clockLabel=new Label("05:00");
		add(clockLabel);
	}
}
