package view.setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PanelEnum;
import view.SettingPanel;
import view.base.Button;
import view.base.Panel;

public class SettingMenuPanel extends Panel {
	//parent
	private SettingPanel parent;
	
	private Button keyBindingButton;
	private Button profileButton;
	private Button volumeButton;
	private Button backButton;
	
	private void setComponents(){
		profileButton=new Button("Profile Setting");
		profileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.toPanel(PanelEnum.PROFILE);
			}
		});
		volumeButton=new Button("Volome Setting");
		volumeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.toPanel(PanelEnum.VOLUMN);
			}
		});		
		keyBindingButton=new Button("KeyBinding Setting");
		keyBindingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.toPanel(PanelEnum.KEYBINDING);
			}
		});
		backButton=new Button("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.back();
			}
		});
		
		add(profileButton);
		add(volumeButton);
		add(keyBindingButton);
		add(backButton);	
	}
	public SettingMenuPanel(SettingPanel panel){
		this.parent=panel;
		setComponents();
	}
	public void toPanel(PanelEnum panelEnum) {
		parent.toPanel(panelEnum);
	}
}
