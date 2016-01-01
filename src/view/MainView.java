package view;

import model.ClientModel;
import view.base.extend.DisplayPanel;
import view.play.PlayPanel;
import view.setting.SettingPanel;

/**
 * view, view logic
 * */
public class MainView extends DisplayPanel{
	private ClientModel clientModel;
	
	private PlayPanel playPanel;
	private SettingPanel settingPanel;
	
	public MainView(){
		setComponents();
		setFrame(new MainFrame());
	}
	
	private void setComponents(){
		//menu
		add(new MainMenuPanel(), PanelEnum.MAINMENU);
		//display
		add(playPanel=new PlayPanel(), PanelEnum.PLAY);
//		playPanel.setModel(getModel());
		add(settingPanel=new SettingPanel(), PanelEnum.SETTING);
//		settingPanel.setModel(getModel());
	}

	public void setFrame(MainFrame mainFrame) {
		mainFrame.add(this);
		addActionListener(mainFrame);//frame listening to MainView, exit event
	}
	
	
	public void setModel(ClientModel clientModel) {
		this.clientModel=clientModel;
	}
}
