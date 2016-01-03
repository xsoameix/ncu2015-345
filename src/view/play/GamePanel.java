package view.play;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.KeyStroke;

import model.game.Player;
import model.game.Result;
import model.game.field.dynamic.Character;
import model.setting.KeyBinding;
import view.base.*;
import view.base.extend.AbstractView;
import view.play.game.*;
import view.play.game.field.object.CharacterView;

public class GamePanel extends AbstractView{
	private RenderThread renderThread;
	private KeyInputTimer keyInputTimer;
	
	//panels
	private ClockPanel clockPanel;
	private TeamPanel teamPanel;
	private PersonalPanel personalPanel;
	private MiniMapPanel miniMapPanel;
	private FieldPanel fieldPanel;
	
	private Button menuButton;
	private MenuDialog menuDialog;
	
	private Character character;
	
	
	public GamePanel(){
		setComponents();
		renderThread=new RenderThread(this);
		keyInputTimer=new KeyInputTimer(this);
	}

	public void requestInputArrowKey(){
		if(moveUnit!=null){
			Point newPoint=newLocation(character.getLocation());
			clientModel.requestSetLocation(newPoint.x, newPoint.y);
		}
	}
	private int unitSize=8;
	private Point moveUnit;
	private Point moveUnits[]={
			new Point(0, -unitSize),
			new Point(0, unitSize),
			new Point(-unitSize, 0),
			new Point(unitSize, 0),
	};
	public void pressArrowKey(int direction) {
		moveUnit=moveUnits[direction-1];
	}
	public void releaseArrowKey(){
		moveUnit=null;
	}
	
	private Point newLocation(Point p){
		Point newPoint=new Point(p);
		newPoint.translate(moveUnit.x, moveUnit.y);
		return newPoint;
	}
	
	private boolean firing=false;
	public void requestInputFireKey(){
		if(firing)
			clientModel.requestFire();
	}
	public void pressFireKey(){
		firing=true;
	}
	public void releaseFireKey(){
		firing=false;
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
		fieldPanel=new FieldPanel();
		fieldPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		miniMapPanel=new MiniMapPanel(fieldPanel);
		miniMapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

	public FieldPanel getFieldPanel() {
		return fieldPanel;
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
		//switch panel
		getDisplayPanel().next();
		fieldPanel.setFocusable(true);
		
		//key
		KeyBinding keyBinding=clientModel.getSetting().getKeyBinding();
		for(KeyStroke keyStroke: keyBinding.keys())
			getInputMap().put(keyStroke, keyBinding.get(keyStroke));
		setInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, getInputMap());
		
		//team and players
//		teamPanel.setTeam(clientModel.getGame().getTeam(clientModel.getIndividual().getTeamID()));
		teamPanel.setTeam(clientModel.getGame().getTeam(1));
		addPlayers(clientModel.getRoom().getPlayerList());
		
		//set field
		
		
		//render and key
		renderThread.start();
		keyInputTimer.start();
		
		
	}
	private void addPlayers(Vector<Player> playerList) {
		int size=playerList.size();
		for(int i=0; i<size; i++){
			CharacterView characterView=new CharacterView(playerList.get(i).getCharacter());
			fieldPanel.addCharacter(playerList.get(i).getCharacter());
			fieldPanel.setComponentZOrder(characterView, 1);
			if(playerList.get(i).getCharacter().getID()==clientModel.getIndividual().getCharacter().getID())
				character=playerList.get(i).getCharacter();
		}
	}

	public void gameOver(Result result) {
		getDisplayPanel().next();
		renderThread.end();
		keyInputTimer.cancel();
	}
}
