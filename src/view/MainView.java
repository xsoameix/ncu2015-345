package view;

import view.base.extend.DisplayPanel;
import view.play.PlayPanel;
import view.setting.SettingPanel;

/**
 * view, view logic
 * */
public class MainView extends DisplayPanel{
	public MainView(){
		setComponents();
		setFrame(new MainFrame());
	}
	
	private void setComponents(){
		//menu
		add(new MainMenuPanel(), PanelEnum.MAINMENU);
		//display
		add(new PlayPanel(), PanelEnum.PLAY);
		add(new SettingPanel(), PanelEnum.SETTING);
	}

	public void setFrame(MainFrame mainFrame) {
		mainFrame.add(this);
		addActionListener(mainFrame);//frame listening to MainView, exit event
	}
}
