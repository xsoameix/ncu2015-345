package view.play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;

import model.ClientModel;
import model.Game;
import model.game.Direction;
import model.game.Result;
import model.game.field.dynamic.Character;
import model.setting.KeyBinding.KeyMap;
import view.base.*;
import view.base.extend.AbstractView;
import view.play.game.*;

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
		
		
		
		//actionMap
		ActionMap actionMap=getActionMap();
		actionMap.put(KeyMap.PRESS_ATTACK, new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	pressFireKey();
	        }
	    });
		actionMap.put(KeyMap.RELEASE_ATTACK, new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	releaseFireKey();
	        }
	    });
		HashMap<KeyMap, Integer> keyMap=new HashMap<KeyMap, Integer>(){
			{
				put(KeyMap.PRESS_UP, Direction.UP);
				put(KeyMap.PRESS_DOWN, Direction.DOWN);
				put(KeyMap.PRESS_LEFT, Direction.LEFT);
				put(KeyMap.PRESS_RIGHT, Direction.RIGHT);
			}
		};
		for(final Entry<KeyMap, Integer> entry:keyMap.entrySet())
			actionMap.put(entry.getKey(), new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	pressArrowKey(entry.getValue());
		        }
		    });
		KeyMap keyMap2[]={KeyMap.RELEASE_UP, KeyMap.RELEASE_DOWN, KeyMap.RELEASE_LEFT, KeyMap.RELEASE_RIGHT};
		for(KeyMap k: keyMap2)
			actionMap.put(k, new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					releaseArrowKey();
				}
			});
	}


	public void requestInputArrowKey(){
		if(moveUnit!=null)
			clientModel.requestSetLocation(newLocation(character.getLocation()));
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
		getDisplayPanel().next();
		setInputMap(WHEN_IN_FOCUSED_WINDOW, clientModel.getSetting().getKeyBinding());
		renderThread.start();
	}
	public void gameOver(Result result) {
		getDisplayPanel().next();
	}
}
