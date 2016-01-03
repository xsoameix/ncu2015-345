package view.setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ClientModel;
import model.Setting;
import view.PanelEnum;
import view.base.extend.DisplayPanel;

public class SettingPanel extends DisplayPanel {
	private ClientModel clientModel;

	private void setComponents(){
		add(new SettingMenuPanel(), PanelEnum.SETTING);
		add(new ProfilePanel(), PanelEnum.PROFILE);
		add(new VolumePanel(), PanelEnum.VOLUMN);
		add(new KeyBindingPanel(), PanelEnum.KEYBINDING);
		
		for(int i=1; i<getComponentCount(); i++)
			addActionListener((ActionListener)getComponent(i));
		
		toPanel(PanelEnum.SETTING);
	}
	public void setModel(ClientModel model){
		this.clientModel=model;
	}
	public SettingPanel() {
		setComponents();
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
