package view.play;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import model.game.Result;
import model.game.Team;
import view.PanelEnum;
import view.base.Button;
import view.base.Label;
import view.base.Panel;
import view.base.extend.AbstractView;

public class ResultPanel extends AbstractView{	
	private Result result;
	
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
	
	private class TeamResultPanel extends Panel{
		private ImageIcon statusIcon;
		private Label teamMoney;
		private Label teamTurf;
		private PlayerResultPanel playerResults[];

		public TeamResultPanel(Team team){
			
		}
		private class PlayerResultPanel{
			
		}
	}
}
