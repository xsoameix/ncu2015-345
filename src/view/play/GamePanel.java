package view.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;
import view.PlayPanel;
import view.base.*;
import view.play.game.*;

public class GamePanel extends Panel{
	private PlayPanel parent;
	
	//panels
	private ClockPanel clockPanel;
	private TeamPanel teamPanel;
	private PersonalPanel personalPanel;
	private MiniMapPanel miniMapPanel;
	private FieldPanel fieldPanel;
	
	private Button menuButton;
	private MenuDialog menuDialog;
	

	private Game game;
	
	private void setComponents(){
		clockPanel=new ClockPanel();
		teamPanel=new TeamPanel();
		personalPanel=new PersonalPanel();
		miniMapPanel=new MiniMapPanel();
		fieldPanel=new FieldPanel();
		add(clockPanel);
		add(teamPanel);
		add(personalPanel);
		add(miniMapPanel);
		add(fieldPanel);
		
		
		menuButton=new Button("Menu");
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuDialog.setVisible(true);
			}
		});
		add(menuButton);
		
		menuDialog=new MenuDialog(parent, "Menu");
	}
	public void setField(){
		fieldPanel.setField(game.getField());
	}
	public GamePanel(PlayPanel parent){
		this.parent=parent;
		setComponents();
	}
}
