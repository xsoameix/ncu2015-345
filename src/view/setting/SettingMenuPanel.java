package view.setting;

import java.awt.event.ActionEvent;
import view.base.Button;
import view.base.Panel;

public class SettingMenuPanel extends Panel {
	private Button keyBindingButton;
	private Button profileButton;
	private Button volumeButton;
	private Button backButton;
	
	private void setComponents(){
		profileButton=new Button("Profile Setting");
		profileButton.setActionCommand("profile");
		profileButton.addActionListener(this);
		
		volumeButton=new Button("Volome Setting");
		volumeButton.setActionCommand("volume");
		volumeButton.addActionListener(this);	
		
		keyBindingButton=new Button("KeyBinding Setting");
		keyBindingButton.setActionCommand("keyBinding");
		keyBindingButton.addActionListener(this);
		
		backButton=new Button("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		
		add(profileButton);
		add(volumeButton);
		add(keyBindingButton);
		add(backButton);	
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
