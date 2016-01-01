package view.setting;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import view.base.Button;
import view.base.Component;
import view.base.Panel;

public class SettingMenuPanel extends Panel {
	private Component[] nullGrids;
	private Button profileButton;
	private Button volumeButton;
	private Button keyBindingButton;
	private Button backButton;
	
	private void setComponents(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10,300,10,300));
		
		nullGrids = new Component[4];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		profileButton=new Button("Profile Setting");
		profileButton.setActionCommand("profile");
		profileButton.addActionListener(this);
		
		volumeButton=new Button("Volume Setting");
		volumeButton.setActionCommand("volume");
		volumeButton.addActionListener(this);
		
		keyBindingButton=new Button("KeyBinding Setting");
		keyBindingButton.setActionCommand("keyBinding");
		keyBindingButton.addActionListener(this);
		
		backButton=new Button("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=0;
		c.gridheight=1;
		c.weightx=0.5;
		c.weighty=0;
		c.insets=new Insets(5, 5, 5, 5);
		c.anchor=GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(profileButton,c);
		
		c.gridy=1;
		c.weightx=0;
		c.fill=GridBagConstraints.BOTH;
		add(nullGrids[0],c);
		
		c.gridy=2;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(volumeButton,c);
		
		c.gridy=3;
		c.fill=GridBagConstraints.BOTH;
		add(nullGrids[1], c);
		
		c.gridy=4;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(keyBindingButton,c);
		
		c.gridy=5;
		c.fill=GridBagConstraints.BOTH;
		add(nullGrids[2], c);
		
		c.gridx=0;
		c.gridy=8;
		c.gridwidth=5;
		c.weightx=0.5;
		c.fill=GridBagConstraints.BOTH;
		add(nullGrids[3], c);
		
		c.gridx=5;
		c.gridy=10;
		c.gridwidth=0;
		c.anchor=GridBagConstraints.EAST;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(backButton,c);	
	}
	public SettingMenuPanel(){
		setComponents();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="back")
			getDisplayPanel().back();
		else
			getDisplayPanel().fireActionEvent(e);
	}
}
