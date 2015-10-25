package view.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PanelEnum;
import view.PlayPanel;
import view.base.Button;
import view.base.Panel;

public class ResultPanel extends Panel{
	private PlayPanel panel;

	private Button nextButton;
	private Button exitButton;
	
	private void setComponents(){
		nextButton=new Button("Next Game");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.toPanel(PanelEnum.ROOM);
			}
		});
		
		exitButton=new Button("exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.toPanel(PanelEnum.ESTABLISH);
			}
		});
		
		add(nextButton);
		add(exitButton);		
	}
	
	public ResultPanel(PlayPanel panel){
		this.panel=panel;
		setComponents();
	}
}
