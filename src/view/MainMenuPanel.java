package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.base.Button;
import view.base.Panel;
/*
 * hold the buttons and pass the actionEvent
 * */
public class MainMenuPanel extends Panel implements ActionListener{
	private Button buttons[];
	
	private void setComponents(){
		buttons=new Button[]{
			new Button("host"),
			new Button("client"),
			new Button("setting"),
			new Button("exit")				
		};
		for(Button button: buttons){
			button.setActionCommand(button.getName());
			button.addActionListener(this);
			add(button);
		}
	}

	public MainMenuPanel() {
		setComponents();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getDisplayPanel().fireActionEvent(e);
	}
}
