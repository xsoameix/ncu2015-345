package view.play;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;

import model.game.Direction;
import model.setting.KeyBinding.KeyMap;

public class KeyInputTimer extends TimerTask{
	private GamePanel gamePanel;
	private Timer timer;
	
	private int DELAY=100;//input delay
	public KeyInputTimer(GamePanel gamePanel) {
		this.gamePanel=gamePanel;
		timer=new Timer();
		
		setActionMap();
	}
	
	private void setActionMap() {
		ActionMap actionMap=gamePanel.getActionMap();
		actionMap.put(KeyMap.PRESS_ATTACK, new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	gamePanel.pressFireKey();
	        }
	    });
		actionMap.put(KeyMap.RELEASE_ATTACK, new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	gamePanel.releaseFireKey();
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
		        	gamePanel.pressArrowKey(entry.getValue());
		        }
		    });
		KeyMap keyMap2[]={KeyMap.RELEASE_UP, KeyMap.RELEASE_DOWN, KeyMap.RELEASE_LEFT, KeyMap.RELEASE_RIGHT};
		for(KeyMap k: keyMap2)
			actionMap.put(k, new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					gamePanel.releaseArrowKey();
				}
			});
	}

	public void start(){
		timer.schedule(this, 0, DELAY);
	}
	public void end(){
		cancel();
	}
	@Override
	public void run() {
		gamePanel.requestInputArrowKey();
		gamePanel.requestInputFireKey();
	}
}
