package view.setting;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.KeyStroke;

import model.setting.KeyBinding;
import model.setting.KeyBinding.KeyMap;
import view.PanelEnum;
import view.base.Button;
import view.base.Component;
import view.base.Label;
import view.base.Panel;
import view.base.TextField;
import view.base.extend.AbstractView;

public class KeyBindingPanel extends AbstractView{
    private KeyBinding newKeyBinding;
    private ErrorKeyBindingDialog errorKeyBindingDialog;
    private Component[] nullGrids;
    private String[] controls;
    private Label[] labels;
    private KeyText[] keyTexts;
	private Button applyButton;
	
	

	private void setComponents(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(50,100,200,100));
		Font font = new Font("Arial", Font.BOLD, 30);
		Insets margin = new Insets(8, 10, 8, 10);
		
		nullGrids = new Component[5];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		controls = new String[]{"UP", "DOWN", "LEFT", "RIGHT", "ATTACK"};

		labels = new Label[5];
		for(int i = 0; i<labels.length; i++) {
			labels[i] = new Label(controls[i]);
			labels[i].setFont(font);
		}
		
		keyTexts = new KeyText[5];
		for(int i = 0; i<keyTexts.length; i++) {
			keyTexts[i] = new KeyText(KeyMap.values()[i]);
			keyTexts[i].setFont(font);
			keyTexts[i].setMargin(margin);
			keyTexts[i].addKeyListener(new KeyTextListener(keyTexts[i]));
		}
		
		applyButton=new Button("Apply");
		applyButton.setFont(font);
		applyButton.setMargin(margin);
		applyButton.setActionCommand("apply");
		applyButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=3;
		c.gridheight=2;
		c.weighty=0;
		c.insets=new Insets(5, 5, 5, 5);
		c.anchor=GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.NONE;
		add(labels[0], c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(keyTexts[0],c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=0;
		add(nullGrids[0],c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(labels[1],c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(keyTexts[1],c);
		
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=0;
		add(nullGrids[1],c);
		
		c.gridx=0;
		c.gridy=8;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(labels[2],c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(keyTexts[2],c);
		
		c.gridx=0;
		c.gridy=10;
		c.gridwidth=0;
		add(nullGrids[2],c);
		
		c.gridx=0;
		c.gridy=12;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(labels[3],c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(keyTexts[3],c);
		
		c.gridx=0;
		c.gridy=14;
		c.gridwidth=0;
		add(nullGrids[3],c);
		
		c.gridx=0;
		c.gridy=16;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(labels[4],c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(keyTexts[4],c);
		
		c.gridx=0;
		c.gridy=18;
		c.gridwidth=4;
		add(nullGrids[4],c);
		
		c.gridx=6;
		c.gridy=24;
		c.gridwidth=0;
		c.anchor=GridBagConstraints.EAST;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(applyButton,c);		
	}
	public KeyBindingPanel(){
		setComponents();
		newKeyBinding=new KeyBinding();
		errorKeyBindingDialog=new ErrorKeyBindingDialog(this, "Error type for control key.");
	}
	
	@Override 
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "keyBinding":
			getDisplayPanel().toPanel(PanelEnum.KEYBINDING);
			for(int i = 0; i<keyTexts.length; i++) {
				keyTexts[i].setText(KeyEvent.getKeyText(newKeyBinding.get(KeyMap.values()[i]).getKeyCode()).toString());
			}
			break;
		case "apply":
			getDisplayPanel().first();
			clientModel.getSetting().setKeyBinding(newKeyBinding);
			break;
		}
	}
	
	private class KeyText extends TextField {
		private KeyMap keyMap;
		public KeyText(KeyMap keyMap){
			this.setKeyMap(keyMap);
			this.setBackground(Color.white);
			this.setFocusable(true);
			this.setEditable(false);
			this.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					KeyText.this.setBorder(null);
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					KeyText.this.setBorder(BorderFactory.createLoweredBevelBorder());
				}
			});
		}
		public KeyMap getKeyMap() {
			return keyMap;
		}
		public void setKeyMap(KeyMap keyMap) {
			this.keyMap = keyMap;
		}
	}
	private class KeyTextListener implements KeyListener{
		private KeyText keyText;
		public KeyTextListener(KeyText keyText) {
			this.keyText=keyText;
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(newKeyBinding.get(KeyStroke.getKeyStrokeForEvent(e)) != null) {
				errorKeyBindingDialog.setVisible(true);
			}
			else {
				int keyCode=e.getKeyCode();
				keyText.setText(KeyEvent.getKeyText(keyCode).toString());
				newKeyBinding.put(KeyStroke.getKeyStrokeForEvent(e), keyText.getKeyMap());
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if(newKeyBinding.get(KeyStroke.getKeyStrokeForEvent(e)) != null) {}
			else {
				int keyCode=e.getKeyCode();
				keyText.setText(KeyEvent.getKeyText(keyCode).toString());
				newKeyBinding.put(KeyStroke.getKeyStrokeForEvent(e), keyText.getKeyMap());
			}
		}		
	}
}
