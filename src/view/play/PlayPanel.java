package view.play;

import java.awt.event.ActionEvent;
import model.ClientModel;
import model.game.Player;
import model.game.Result;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Character;
import model.game.field.dynamic.Obstacle;
import view.PanelEnum;
import view.base.extend.DisplayPanel;

public class PlayPanel extends DisplayPanel{
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
	@Override
	public void setModel(ClientModel clientModel) {
		super.setModel(clientModel);
		clientModel.setPlayPanel(this);
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

	//room
	public void addPlayer(Player player){
		roomPanel.addPlayer(player);
	}
	public void removePlayer(Player player){
		roomPanel.removePlayer(player);
	}
	//field
	public void addBullet(Bullet bullet){
		gamePanel.getFieldPanel().addBullet(bullet);
	}
	public void removeBullet(Bullet bullet){
		gamePanel.getFieldPanel().removeBullet(bullet);
	}
	public void addObstacle(Obstacle obstacle){
		gamePanel.getFieldPanel().addObstacle(obstacle);
	}
	public void removeObstacle(Obstacle obstacle){
		gamePanel.getFieldPanel().removeObstacle(obstacle);
	}
	public void addCharacter(Character character){
		gamePanel.getFieldPanel().addCharacter(character);
	}
	public void removeCharacter(Character character){
		gamePanel.getFieldPanel().removeCharacter(character);
	}
	//game
	public void startGame(){
		gamePanel.startGame();
	}
	public void gameOver(Result result){
		gamePanel.gameOver(result);
	}
}
