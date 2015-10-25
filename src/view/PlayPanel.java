package view;

import java.awt.CardLayout;

import model.Application;
import view.base.Panel;
import view.play.*;

public class PlayPanel extends Panel {
	private MainFrame parent;
	private Application application;
	
	private CardLayout cardLayout;
	
	private EstablishPanel establishPanel;
	private RoomPanel roomPanel;
	private GamePanel gamePanel;
	private ResultPanel resultPanel;

	public PlayPanel(MainFrame parent) {
		this.parent = parent;
		this.application = parent.getApplication();
		setComponents();
	}	
	
	private void setComponents() {
		cardLayout=new CardLayout();
		setLayout(cardLayout);
		
		establishPanel=new EstablishPanel(this);
		roomPanel=new RoomPanel(this);
		gamePanel=new GamePanel(this);
		resultPanel=new ResultPanel(this);
		
		add(establishPanel, PanelEnum.ESTABLISH);
		add(roomPanel, PanelEnum.ROOM);
		add(gamePanel, PanelEnum.GAME);
		add(resultPanel, PanelEnum.RESULT);
	}
	private void add(Panel panel, PanelEnum panelEnum){
		add(panel, panelEnum.toString());
	}

	public void toPanel(PanelEnum panelEnum){
		cardLayout.show(this, panelEnum.toString());
	}
	public void back(){
		parent.toPanel(PanelEnum.MAINMENU);
	}
	
	public void setHost(boolean isHost) {
		establishPanel.setHost(isHost);
	}
	public void establishRoom() {
		application.establishRoom();
		roomPanel.setRoom(application.getRoom());
	}
	public void establishGame() {
		application.getRoom().establishGame();
	}


}
