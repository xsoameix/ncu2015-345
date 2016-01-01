package view.setting;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.PanelEnum;
import view.base.Button;
import view.base.Component;
import view.base.Panel;

public class VolumePanel extends Panel{
	
	private Component[] nullGrids;
	private JLabel volume;
	private SoundPlayer sound;
	private Button volumeUpButton;
	private Button volumeDownButton;
	private Button backButton;
	
	
	private void setComponents(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(100,300,100,300));
		
		nullGrids = new Component[3];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		volume=new JLabel("Volume");
		//sound=new SoundPlayer();
		
		volumeDownButton=new Button("DOWN");
		volumeDownButton.setActionCommand("volumeDown");
		volumeDownButton.addActionListener(this);
		
		volumeUpButton=new Button("UP");
		volumeUpButton.setActionCommand("volumeUp");
		volumeUpButton.addActionListener(this);
		
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
		c.fill=GridBagConstraints.NONE;
		add(volume,c);
		
		c.gridy=1;
		add(nullGrids[0],c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(volumeDownButton,c);
		
		c.gridx=1;
		c.gridwidth=1;
		add(nullGrids[1],c);
		
		c.gridx=2;
		c.gridwidth=0;
		add(volumeUpButton,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=0;
		add(nullGrids[2],c);
		
		c.gridx=4;
		c.gridwidth=0;
		c.anchor=GridBagConstraints.EAST;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(backButton,c);
	}
	public VolumePanel(){
		setComponents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "volume":
			getDisplayPanel().toPanel(PanelEnum.VOLUMN);
			break;
		case "volumeDown":
			//sound.setVolume(sound.getVolume()-1.0f);
			break;
		case "volumeUp":
			//sound.setVolume(sound.getVolume()+1.0f);
			break;
		case "back":
			getDisplayPanel().first();
			break;
		}
	}
}
