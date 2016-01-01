package view.setting;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.management.loading.PrivateClassLoader;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.setting.KeyBinding;
import model.setting.KeyBinding.KeyMap;
import view.PanelEnum;
import view.base.Button;
import view.base.Component;
import view.base.Label;
import view.base.Panel;
import view.base.TextField;

public class KeyBindingPanel extends Panel {
	
    private KeyBinding keyBinding;
    private Component[] nullGrids;
	private Label upLabel;
	private Label downLabel;
	private Label leftLabel;
	private Label rightLabel;
	private Label attackLabel;
	private TextField upTextField;
	private TextField downTextField;
	private TextField leftTextField;
	private TextField rightTextField;
	private TextField attackTextField;
	private Button applyButton;
	
	private void setComponents(){
		//this is temporary setting!
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(50,100,50,200));
		//
		
		nullGrids = new Component[5];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		upLabel=new Label("UP");
		downLabel=new Label("DOWN");
		leftLabel=new Label("LEFT");
		rightLabel=new Label("RIGHT");
		attackLabel=new Label("ATTACK");
		upTextField=new TextField();
		downTextField=new TextField();
		leftTextField=new TextField();
		rightTextField=new TextField();
		attackTextField=new TextField();
		
		applyButton=new Button("Apply");
		applyButton.setActionCommand("apply");
		applyButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=1;
		c.gridheight=2;
		c.weightx=0.5;
		c.weighty=0;
		c.insets=new Insets(5, 5, 5, 5);
		c.anchor=GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.NONE;
		add(upLabel, c);
		
		c.gridx=1;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(upTextField,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=0;
		add(nullGrids[0],c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=1;
		c.fill=GridBagConstraints.NONE;
		add(downLabel,c);
		
		c.gridx=1;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(downTextField,c);
		
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=0;
		add(nullGrids[1],c);
		
		c.gridx=0;
		c.gridy=8;
		c.gridwidth=1;
		c.fill=GridBagConstraints.NONE;
		add(leftLabel,c);
		
		c.gridx=1;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(leftTextField,c);
		
		c.gridx=0;
		c.gridy=10;
		c.gridwidth=0;
		add(nullGrids[2],c);
		
		c.gridx=0;
		c.gridy=12;
		c.gridwidth=1;
		c.fill=GridBagConstraints.NONE;
		add(rightLabel,c);
		
		c.gridx=1;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(rightTextField,c);
		
		c.gridx=0;
		c.gridy=14;
		c.gridwidth=0;
		add(nullGrids[3],c);
		
		c.gridx=0;
		c.gridy=16;
		c.gridwidth=1;
		c.fill=GridBagConstraints.NONE;
		add(attackLabel,c);
		
		c.gridx=1;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(attackTextField,c);
		
		c.gridx=0;
		c.gridy=20;
		c.gridwidth=4;
		c.gridheight=2;
		add(nullGrids[4],c);
		
		c.gridx=6;
		c.gridwidth=0;
		c.anchor=GridBagConstraints.EAST;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(applyButton,c);		
	}
	public KeyBindingPanel(){
		setComponents();
	}
	
	public void setKeyBinding(KeyBinding keyBinding) {
		this.keyBinding = keyBinding;
	}
	
	@Override 
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "keyBinding":
			getDisplayPanel().toPanel(PanelEnum.KEYBINDING);
			upTextField.setText("UP");
			upTextField.setEditable(true);
			downTextField.setText("DOWN");
			downTextField.setEditable(true);
			leftTextField.setText("LEFT");
			leftTextField.setEditable(true);
			rightTextField.setText("RIGHT");
			rightTextField.setEditable(true);
			attackTextField.setText("ATTACK");
			attackTextField.setEditable(true);
			break;
		case "apply":
//		    keyBinding.put(KeyStroke.getKeyStroke(upTextField.getText()), KeyMap.UP);
//		    keyBinding.put(KeyStroke.getKeyStroke(downTextField.getText()), KeyMap.DOWN);
//		    keyBinding.put(KeyStroke.getKeyStroke(leftTextField.getText()), KeyMap.LEFT);
//		    keyBinding.put(KeyStroke.getKeyStroke(rightTextField.getText()), KeyMap.RIGHT);
			getDisplayPanel().first();
			break;
		}
	}
}
