package view.setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PanelEnum;
import view.SettingPanel;
import view.base.Button;
import view.base.Label;
import view.base.Panel;
import view.base.TextField;

public class ProfilePanel extends Panel {
	private SettingPanel parent;
	
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
		backButton=new Button("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.toPanel(PanelEnum.SETTING);
			}
		});
		add(applyButton);
		add(backButton);
	}
	public ProfilePanel(SettingPanel parent){
		this.parent=parent;
		setComponents();
	}
}
