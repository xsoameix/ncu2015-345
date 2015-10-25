package view.setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PanelEnum;
import view.SettingPanel;
import view.base.Button;
import view.base.Panel;

public class VolumnPanel extends Panel{
	private SettingPanel parent;

	private Button backButton;
	private void setComponents(){
		backButton=new Button("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.toPanel(PanelEnum.SETTING);
			}
		});
		add(backButton);
	}
	public VolumnPanel(SettingPanel parent){
		this.parent=parent;
		setComponents();
	}
}
