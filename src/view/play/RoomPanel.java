package view.play;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;

import model.game.Player;
import view.base.Button;
import view.base.extend.AbstractView;
import view.play.room.GameSettingPanel;
import view.play.room.PlayerView;
import view.play.room.PlayersPanel;

public class RoomPanel extends AbstractView{
	private JSplitPane splitPane;
	private PlayersPanel playersPanel;
	private GameSettingPanel gameSettingPanel;
	
	private Button buttons[];
	
	private void setPanels(){
		setLayout(new GridLayout());
		playersPanel=new PlayersPanel();
		gameSettingPanel=new GameSettingPanel();
		
		splitPane=new JSplitPane();
		splitPane.setLeftComponent(playersPanel);
		splitPane.setRightComponent(gameSettingPanel);
		splitPane.setDividerLocation(1000);
		splitPane.setDividerSize(0);
//		add(playersPanel);
//		add(gameSettingPanel);
		add(splitPane);
	}
	private void setButtons(){
		buttons=new Button[]{
				new Button("start"),
				new Button("back")
		};
		for(Button button: buttons){
			button.addActionListener(this);
			button.setActionCommand(button.getName());
			gameSettingPanel.add(button);
		}
	}
	private void setComponents(){
		setPanels();
		setButtons();
	}
	public RoomPanel(){
		setComponents();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "start":
			clientModel.requestStartGame();
			break;
		case "back"://TODO: remove
			//leave room
			getDisplayPanel().previous();
			break;
		}
	}
	
	//API
	public void addPlayer(Player player) {
		playersPanel.addPlayer(player);
		repaint();
	}
	public void removePlayer(Player player) {
		for(Component component: playersPanel.getComponents())
			if(((PlayerView)component).getPlayer().equals(player)){
				playersPanel.remove(component);
				repaint();
			}
	}
}
