package view.setting;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import view.PanelEnum;
import view.base.Button;
import view.base.Component;
import view.base.Label;
import view.base.Panel;

public class VolumePanel extends Panel{
	
	private Component[] nullGrids;
	private ImageIcon[] statusIcons;
	private Label volume;
	private int statusID;
	private Label status;
//	private SoundPlayer sound;
	private Button volumeUpButton;
	private Button volumeDownButton;
	private Button backButton;
	
	
	private void setComponents(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(50,100,200,100));
		Font font = new Font("Arial", Font.BOLD, 30);
		Insets margin = new Insets(8, 10, 8, 10);
		
		nullGrids = new Component[3];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		statusIcons=new ImageIcon[6];
		for(int i = 0; i<statusIcons.length; i++) {
			statusIcons[i]=new ImageIcon("image/"+Integer.toString(i*20)+".png");
		}
		
		volume=new Label("VOLUME");
		volume.setFont(font);
		volume.setIcon(new ImageIcon("image/volume.png"));
		statusID=3;
		status=new Label("");
		status.setIcon(statusIcons[statusID]);
		
//		sound=new SoundPlayer();
//		sound.initialize("music/bg2.wav");
		
		volumeDownButton=new Button("DOWN");
		volumeDownButton.setFont(font);
		volumeDownButton.setMargin(margin);
		volumeDownButton.setIcon(new ImageIcon("image/volumedown.png"));
		volumeDownButton.setActionCommand("volumeDown");
		volumeDownButton.addActionListener(this);
		
		volumeUpButton=new Button(" UP ");
		volumeUpButton.setFont(font);
		volumeUpButton.setMargin(margin);
		volumeUpButton.setIcon(new ImageIcon("image/volumeup.png"));
		volumeUpButton.setActionCommand("volumeUp");
		volumeUpButton.addActionListener(this);
		
		backButton=new Button("Back");
		backButton.setFont(font);
		backButton.setMargin(margin);
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=0;
		c.gridheight=2;
		c.weighty=0;
		c.insets=new Insets(5, 5, 5, 5);
		c.anchor=GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.NONE;
		add(volume,c);
		
		c.gridy=2;
		add(nullGrids[0],c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(volumeDownButton,c);
		
		c.gridx=4;
		c.gridwidth=4;
		add(status,c);
		
		c.gridx=9;
		c.gridwidth=0;
		add(volumeUpButton,c);
		
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=0;
		add(nullGrids[2],c);
		
		c.gridx=9;
		c.gridy=10;
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
//			if(sound.limit(sound.getVolume()-2.0f)) {
//				sound.setVolume(sound.getVolume()-2.0f);
//				statusID=statusID-1;
//				status.setIcon(statusIcons[statusID]);
//			}
			break;
		case "volumeUp":
//			if(sound.limit(sound.getVolume()+2.0f)) {
//				sound.setVolume(sound.getVolume()+2.0f);
//				statusID=statusID+1;
//				status.setIcon(statusIcons[statusID]);
//			}
			break;
		case "back":
			getDisplayPanel().first();
			break;
		}
	}
}
