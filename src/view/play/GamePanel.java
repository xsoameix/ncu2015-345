package view.play;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import model.Game;
import model.game.Result;
import view.base.*;
import view.play.game.*;

public class GamePanel extends Panel{
	private RenderThread renderThread;
	
	//panels
	private ClockPanel clockPanel;
	private TeamPanel teamPanel;
	private PersonalPanel personalPanel;
	private MiniMapPanel miniMapPanel;
	private FieldPanel fieldPanel;
	
	private Button menuButton;
	private MenuDialog menuDialog;

	private Game game;
	
	public GamePanel(){
		setComponents();
		renderThread=new RenderThread(this);
	}
	private void setComponents(){
		GridBagLayout gridBagLayout=new GridBagLayout();

		setLayout(gridBagLayout);	
		
		
		GridBagConstraints clockConstraints=new GridBagConstraints();
		clockConstraints.fill=GridBagConstraints.BOTH;

		GridBagConstraints teamConstraints=new GridBagConstraints();
		teamConstraints.gridwidth=8;
		teamConstraints.weightx=1;
		teamConstraints.fill=GridBagConstraints.BOTH;

		GridBagConstraints menuConstraints=new GridBagConstraints();
		menuConstraints.gridheight=teamConstraints.gridheight;
		menuConstraints.gridwidth=GridBagConstraints.REMAINDER;
		menuConstraints.fill=GridBagConstraints.BOTH;

		GridBagConstraints fieldConstraints=new GridBagConstraints();
		fieldConstraints.gridheight=GridBagConstraints.REMAINDER;
		fieldConstraints.gridwidth=8;
		fieldConstraints.weightx=10;
		fieldConstraints.weighty=1;
		fieldConstraints.fill=GridBagConstraints.BOTH;

		GridBagConstraints personalConstraints=new GridBagConstraints();
		personalConstraints.gridheight=GridBagConstraints.RELATIVE;
		personalConstraints.gridwidth=GridBagConstraints.REMAINDER;
		personalConstraints.weighty=1;
		personalConstraints.fill=GridBagConstraints.BOTH;

		GridBagConstraints miniMapConstraints=new GridBagConstraints();
//		miniMapConstraints.gridx=personalConstraints.gridx;
//		miniMapConstraints.gridy=personalConstraints.gridy+personalConstraints.gridheight;
		miniMapConstraints.gridheight=GridBagConstraints.RELATIVE;
		miniMapConstraints.gridwidth=GridBagConstraints.REMAINDER;
		miniMapConstraints.weighty=1;
		miniMapConstraints.fill=GridBagConstraints.BOTH;
//		miniMapConstraints.anchor=GridBagConstraints.CENTER;
		
		clockPanel=new ClockPanel();
		clockPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		teamPanel=new TeamPanel();
		teamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		personalPanel=new PersonalPanel();
		personalPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		miniMapPanel=new MiniMapPanel();
		miniMapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fieldPanel=new FieldPanel();
		fieldPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuButton=new Button("Menu");
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuDialog.setVisible(true);
			}
		});		
		
		add(clockPanel, clockConstraints);
		add(teamPanel, teamConstraints);
		add(menuButton, menuConstraints);
		add(fieldPanel, fieldConstraints);
		add(personalPanel, personalConstraints);
		add(miniMapPanel, miniMapConstraints);

		menuDialog=new MenuDialog(this, "Menu");
	}
	public void setField(){
		fieldPanel.setField(getGame().getField());
	}
	public FieldPanel getFieldPanel() {
		return fieldPanel;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		menuDialog.dispose();
		switch(e.getActionCommand()){
		case "leave"://leave game
			getDisplayPanel().previous();
			break;
		case "result"://to be removed, go to result
			getDisplayPanel().next();
			break;
		}
	}
	

	public void startGame(){
		getDisplayPanel().next();
		renderThread.start();
	}
	public void gameOver(Result result) {
		getDisplayPanel().next();
	}
}
