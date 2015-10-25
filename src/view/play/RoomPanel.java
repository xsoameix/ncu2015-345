package view.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Room;
import view.PanelEnum;
import view.PlayPanel;
import view.base.*;
import view.play.room.GameSettingPanel;
import view.play.room.PlayersPanel;

public class RoomPanel extends Panel{
	
	private PlayPanel parent;
	
	private PlayersPanel playersPanel;
	private GameSettingPanel gameSettingPanel;
	
	private Button lockButton;//all players check and start game
	private Button backButton;
	
	private Room room;
	private void setPanels(){
		playersPanel=new PlayersPanel();
		gameSettingPanel=new GameSettingPanel();
		add(playersPanel);
		add(gameSettingPanel);
	}
	private void setButtons(){
		//lock
		lockButton=new Button("Lock");
		lockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.toPanel(PanelEnum.GAME);
				parent.establishGame();
			}
		});
		add(lockButton);
		
		
		//back
		backButton=new Button("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.toPanel(PanelEnum.ESTABLISH);
			}
		});
		add(backButton);
	}
	private void setComponents(){
		setPanels();
		setButtons();
	}
	public void setRoom(Room room){
		this.room=room;
	}
	public Room getRoom(){
		return room;
	}
	public RoomPanel(PlayPanel panel){
		this.parent=panel;
		
		setComponents();
	}
}
