package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import view.base.Button;
import view.base.Panel;
/*
 * hold the buttons and pass the actionEvent
 * */
public class MainMenuPanel extends Panel implements ActionListener{
	private Button buttons[];
	
	private void setComponents(){
		setLayout(new GridLayout(0, 1, 50, 50));
		setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300));
		buttons=new Button[]{
			new Button("host"),
			new Button("client"),
			new Button("setting"),
			new Button("exit")				
		};
		Font font = new Font("Arial", Font.BOLD, 30);
		for(Button button: buttons){
			button.setActionCommand(button.getName());
			button.addActionListener(this);
			button.setFont(font);
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
