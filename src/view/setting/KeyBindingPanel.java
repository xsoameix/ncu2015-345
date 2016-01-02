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

public class KeyBindingPanel extends Panel{
	
    private KeyBinding keyBinding;
    private KeyBinding newKeyBinding;
    private ErrorKeyBindingDialog errorKeyBindingDialog;
    private Component[] nullGrids;
	private Label upLabel;
	private Label downLabel;
	private Label leftLabel;
	private Label rightLabel;
	private Label attackLabel;
	private KeyText upTextField;
	private KeyText downTextField;
	private KeyText leftTextField;
	private KeyText rightTextField;
	private KeyText attackTextField;
	private Button applyButton;
	
	

	private void setComponents(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(50,100,200,100));
		Font font = new Font("", Font.BOLD, 30);
		
		nullGrids = new Component[5];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		upLabel=new Label("UP");
		upLabel.setFont(font);
		downLabel=new Label("DOWN");
		downLabel.setFont(font);
		leftLabel=new Label("LEFT");
		leftLabel.setFont(font);
		rightLabel=new Label("RIGHT");
		rightLabel.setFont(font);
		attackLabel=new Label("ATTACK");
		attackLabel.setFont(font);
		upTextField=new KeyText(KeyMap.UP);
		upTextField.setFont(font);
		upTextField.addKeyListener(new KeyTextListener(upTextField));
		downTextField=new KeyText(KeyMap.DOWN);
		downTextField.setFont(font);
		downTextField.addKeyListener(new KeyTextListener(downTextField));
		leftTextField=new KeyText(KeyMap.LEFT);
		leftTextField.setFont(font);
		leftTextField.addKeyListener(new KeyTextListener(leftTextField));
		rightTextField=new KeyText(KeyMap.RIGHT);
		rightTextField.setFont(font);
		rightTextField.addKeyListener(new KeyTextListener(rightTextField));
		attackTextField=new KeyText(KeyMap.ATTACK);
		attackTextField.setFont(font);
		attackTextField.addKeyListener(new KeyTextListener(attackTextField));
		
		applyButton=new Button("Apply");
		applyButton.setFont(font);
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
		add(upLabel, c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(upTextField,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=0;
		add(nullGrids[0],c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(downLabel,c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(downTextField,c);
		
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=0;
		add(nullGrids[1],c);
		
		c.gridx=0;
		c.gridy=8;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(leftLabel,c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(leftTextField,c);
		
		c.gridx=0;
		c.gridy=10;
		c.gridwidth=0;
		add(nullGrids[2],c);
		
		c.gridx=0;
		c.gridy=12;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(rightLabel,c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(rightTextField,c);
		
		c.gridx=0;
		c.gridy=14;
		c.gridwidth=0;
		add(nullGrids[3],c);
		
		c.gridx=0;
		c.gridy=16;
		c.gridwidth=3;
		c.fill=GridBagConstraints.NONE;
		add(attackLabel,c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(attackTextField,c);
		
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
	
	public void setKeyBinding(KeyBinding keyBinding) {
		this.keyBinding = keyBinding;
		
	}
	
	@Override 
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "keyBinding":
			getDisplayPanel().toPanel(PanelEnum.KEYBINDING);
			upTextField.setText(KeyEvent.getKeyText(newKeyBinding.get(KeyMap.UP).getKeyCode()).toString());
			downTextField.setText(KeyEvent.getKeyText(newKeyBinding.get(KeyMap.DOWN).getKeyCode()).toString());
			leftTextField.setText(KeyEvent.getKeyText(newKeyBinding.get(KeyMap.LEFT).getKeyCode()).toString());
			rightTextField.setText(KeyEvent.getKeyText(newKeyBinding.get(KeyMap.RIGHT).getKeyCode()).toString());
			attackTextField.setText(KeyEvent.getKeyText(newKeyBinding.get(KeyMap.ATTACK).getKeyCode()).toString());
			break;
		case "apply":
			getDisplayPanel().first();
			keyBinding=newKeyBinding;
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
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if(newKeyBinding.get(KeyStroke.getKeyStrokeForEvent(e)) != null) {
				errorKeyBindingDialog.setVisible(true);
			}
			else {
				int keyCode=e.getKeyCode();
				keyText.setText(KeyEvent.getKeyText(keyCode).toString());
				newKeyBinding.put(KeyStroke.getKeyStrokeForEvent(e), keyText.getKeyMap());
			}
		}		
	}
}
