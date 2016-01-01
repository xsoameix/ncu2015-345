package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Setting;
import view.base.extend.DisplayPanel;
import view.setting.KeyBindingPanel;
import view.setting.ProfilePanel;
import view.setting.SettingMenuPanel;
import view.setting.VolumnPanel;

public class SettingPanel extends DisplayPanel {
	private Setting setting;

	private void setComponents(){
		add(new SettingMenuPanel(), PanelEnum.SETTING);
		add(new ProfilePanel(), PanelEnum.PROFILE);
		add(new VolumnPanel(), PanelEnum.VOLUMN);
		add(new KeyBindingPanel(), PanelEnum.KEYBINDING);
		
		for(int i=1; i<getComponentCount(); i++)
			addActionListener((ActionListener)getComponent(i));
		
		toPanel(PanelEnum.SETTING);
	}

	public SettingPanel() {
		setComponents();
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "setting":
			getDisplayPanel().toPanel(PanelEnum.SETTING);
			break;
		default:
			fireActionEvent(e);
			break;
		}
	}
}
