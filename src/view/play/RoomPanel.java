package view.play;

import java.awt.event.ActionEvent;

import model.Room;
import view.base.Button;
import view.base.Panel;
import view.play.room.GameSettingPanel;
import view.play.room.PlayerPanel;

public class RoomPanel extends Panel{
	private Room room;
	
	private PlayerPanel playersPanel;
	private GameSettingPanel gameSettingPanel;
	
	private Button buttons[];
	
	
	
	private void setPanels(){
		playersPanel=new PlayerPanel();
		gameSettingPanel=new GameSettingPanel();
		add(playersPanel);
		add(gameSettingPanel);
	}
	private void setButtons(){
		buttons=new Button[]{
				new Button("start"),
				new Button("back")
		};
		for(Button button: buttons){
			button.addActionListener(this);
			button.setActionCommand(button.getName());
			add(button);
		}
	}
	private void setComponents(){
		setPanels();
		setButtons();
	}
	public RoomPanel(){
		setComponents();
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "start":
			//clientModel.requestStartGame()
			getDisplayPanel().next();
			break;
		case "back":
			//leave room
			getDisplayPanel().previous();
			break;
		}
	}

}
