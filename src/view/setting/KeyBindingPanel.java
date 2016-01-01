package view.setting;

import java.awt.event.ActionEvent;
import view.PanelEnum;
import view.base.Button;
import view.base.Panel;

public class KeyBindingPanel extends Panel {
	private Button backButton;
	private void setComponents(){
		backButton=new Button("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		add(backButton);
	}
	public KeyBindingPanel(){
		setComponents();
	}
	
	@Override 
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "keyBinding":
			getDisplayPanel().toPanel(PanelEnum.KEYBINDING);
			break;
		case "back":
			getDisplayPanel().first();
			break;
		}
	}
}
