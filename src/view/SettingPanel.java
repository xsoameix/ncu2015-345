package view;

import java.awt.CardLayout;

import model.Setting;
import view.base.Panel;
import view.setting.KeyBindingPanel;
import view.setting.ProfilePanel;
import view.setting.SettingMenuPanel;
import view.setting.VolumnPanel;

public class SettingPanel extends Panel {
	private MainFrame parent;
	
	private CardLayout cardLayout;
	
	private Setting setting;

	private void setComponents(){
		cardLayout=new CardLayout();
		setLayout(cardLayout);
		
		add(new SettingMenuPanel(this), PanelEnum.SETTING);
		add(new ProfilePanel(this), PanelEnum.PROFILE);
		add(new VolumnPanel(this), PanelEnum.VOLUMN);
		add(new KeyBindingPanel(this), PanelEnum.KEYBINDING);
		
		toPanel(PanelEnum.SETTING);
	}
	public void add(Panel panel, PanelEnum panelEnum){
		add(panel, panelEnum.toString());
	}
	public SettingPanel(MainFrame parent){
		this.parent=parent;
		setComponents();
	}
	public void toPanel(PanelEnum panelEnum){
		cardLayout.show(this, panelEnum.toString());
	}
	public void back(){
		parent.toPanel(PanelEnum.MAINMENU);
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
}
