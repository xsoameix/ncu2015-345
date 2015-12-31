package view.play;

import java.awt.event.ActionEvent;

import view.PanelEnum;
import view.base.Button;
import view.base.Panel;

public class ResultPanel extends Panel{	
//	private Result result;
	
	//to be removed
	private Button nextButton;
	
	private Button exitButton;
	
	private void setComponents(){
		//to be removed
		nextButton=new Button("next");
		nextButton.setActionCommand(nextButton.getName());
		nextButton.addActionListener(this);
		
		exitButton=new Button("exit");
		exitButton.setActionCommand(exitButton.getName());
		exitButton.addActionListener(this);
		
		add(nextButton);
		add(exitButton);		
	}
	
	public ResultPanel(){
		setComponents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "next":
			getDisplayPanel().toPanel(PanelEnum.ROOM);
			break;
		case "exit":
			getDisplayPanel().back();
			break;
		}
	}
}
