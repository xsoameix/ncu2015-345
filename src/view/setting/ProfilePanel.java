package view.setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PanelEnum;
import view.base.Button;
import view.base.Label;
import view.base.Panel;
import view.base.TextField;

public class ProfilePanel extends Panel {
	private Label nameLabel;
	private TextField nameTextField;
	private Label imageLabel;
	private Label imageContentLabel;
	
	private Button applyButton;
	private Button backButton;
	
	private void setComponents(){
		nameLabel=new Label("Name");
		nameTextField=new TextField();
		imageLabel=new Label("Image");
		imageContentLabel=new Label("");
		
		add(nameLabel);
		add(nameTextField);
		add(imageLabel);
		add(imageContentLabel);
		
		applyButton=new Button("Apply");
		applyButton.addActionListener(this);
		backButton=new Button("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		add(applyButton);
		add(backButton);
	}
	public ProfilePanel(){
		setComponents();
	}
	
	@Override 
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "profile":
			getDisplayPanel().toPanel(PanelEnum.KEYBINDING);
			break;
		case "back":
			getDisplayPanel().first();
			break;
		}
	}
}
