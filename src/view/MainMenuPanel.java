package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.base.Button;
import view.base.Panel;

public class MainMenuPanel extends Panel{
	private MainFrame parent;
	
	private Button hostButton;
	private Button clientButton;
	private Button settingButton;
	private Button exitButton;
	
	private void setComponents(){
		//host
		hostButton=new Button("host");
		hostButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.getPlayPanel().setHost(true);
				parent.toPanel(PanelEnum.PLAY);
				//UIController.establishRoom(true);
			}
		});
		add(hostButton);

		//client
		clientButton=new Button("client");
		clientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.getPlayPanel().setHost(false);
				parent.toPanel(PanelEnum.PLAY);
				//UIController.establishRoom(false);
			}
		});
		add(clientButton);

		//setting
		settingButton=new Button("setting");
		settingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.toPanel(PanelEnum.SETTING);
				//UIController.setting();
			}
		});
		add(settingButton);
		
		//exit
		exitButton=new Button("exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
				//UIController.exit();
			}
		});
		add(exitButton);
		
	}
	
	public MainMenuPanel(MainFrame parent){
		this.parent=parent;
		setComponents();
	}
	public void toPanel(PanelEnum panelEnum) {
		parent.toPanel(panelEnum);
	}

}
