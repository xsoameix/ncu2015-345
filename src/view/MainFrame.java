package view;

import java.awt.CardLayout;
import model.Application;
import view.base.Frame;
import view.base.Panel;


public class MainFrame extends Frame{
	//model
	private Application application;
	
	private CardLayout cardLayout;
	
	private PlayPanel playPanel;
	
	private void setComponents()
	{
		//layout
		cardLayout=new CardLayout();
		setLayout(cardLayout);
		
		//menu
		add(new MainMenuPanel(this), PanelEnum.MAINMENU);
		
		add(playPanel=new PlayPanel(this), PanelEnum.PLAY);
		add(new SettingPanel(this), PanelEnum.SETTING);
		
		toPanel(PanelEnum.MAINMENU);
	}
	public void add(Panel panel, PanelEnum panelEnum){
		add(panel, panelEnum.toString());
	}
	
	public MainFrame(Application application){
		this.setApplication(application);
		
		//frame initialization
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Config.frameDimension);
		
		setComponents();
		
		//show frame
		setVisible(true);
	}
	public void toPanel(PanelEnum panelEnum){
		cardLayout.show(getContentPane(), panelEnum.toString());
	}
	
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public PlayPanel getPlayPanel() {
		return playPanel;
	}
}
