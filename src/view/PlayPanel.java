package view;

import java.awt.event.ActionEvent;
import model.ClientModel;
import view.base.extend.DisplayPanel;
import view.play.EstablishPanel;
import view.play.GamePanel;
import view.play.ResultPanel;
import view.play.RoomPanel;

public class PlayPanel extends DisplayPanel{
	private ClientModel clientModel;
	
	private EstablishPanel establishPanel;
	private RoomPanel roomPanel;
	private GamePanel gamePanel;
	private ResultPanel resultPanel;

	public PlayPanel() {
		setComponents();
	}
	private void setComponents() {
		establishPanel=new EstablishPanel();
		addActionListener(establishPanel);
		roomPanel=new RoomPanel();
		gamePanel=new GamePanel();
		resultPanel=new ResultPanel();
		
		add(establishPanel, PanelEnum.ESTABLISH);
		add(roomPanel, PanelEnum.ROOM);
		add(gamePanel, PanelEnum.GAME);
		add(resultPanel, PanelEnum.RESULT);
	}
	public ClientModel getMainModel() {
		return clientModel;
	}
	public void setMainModel(ClientModel mainModel) {
		this.clientModel = mainModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "host":
		case "client":
			getDisplayPanel().toPanel(PanelEnum.PLAY);
			fireActionEvent(e);
			break;
		}
	}

}
