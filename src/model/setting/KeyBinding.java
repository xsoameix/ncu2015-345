package model.setting;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class KeyBinding extends InputMap {
	private HashMap<KeyMap, KeyStroke> map;
	public enum KeyMap {
		UP, DOWN, LEFT, RIGHT, ATTACK 
	}
	
	@Override
	public void put(KeyStroke keyStroke, Object actionMapKey){
		remove(map.get(actionMapKey));
		super.put(keyStroke, actionMapKey);
		map.remove(actionMapKey);
		map.put((KeyMap) actionMapKey, keyStroke);
	}
	public KeyStroke get(KeyMap keyMap){
		return map.get(keyMap);
	}
	
	public KeyBinding() {
		super();
		map=new HashMap<KeyMap, KeyStroke>();
		put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), KeyMap.ATTACK);
		put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), KeyMap.UP);
		put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), KeyMap.DOWN);
		put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), KeyMap.LEFT);
		put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), KeyMap.RIGHT);
	}
}
