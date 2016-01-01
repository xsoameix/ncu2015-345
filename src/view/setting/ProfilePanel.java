package view.setting;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.Parent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.setting.Profile;
import view.PanelEnum;
import view.base.Button;
import view.base.Component;
import view.base.Label;
import view.base.Panel;
import view.base.TextField;

public class ProfilePanel extends Panel {
	private Profile profile;
	
	private Component[] nullGrids;
	private Label nameLabel;
	private TextField nameTextField;
	private Label imageLabel;
	private Button imageContentButton;
	private IconDialog iconDialog;
	
	private Button applyButton;
	private Button backButton;
	
	private void setComponents(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(100, 100, 50, 200));
		
		nullGrids = new Component[2];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		nameLabel=new Label("Your Name:");
		nameTextField=new TextField();
		imageLabel=new Label("Your Image:");
		imageContentButton=new Button("Choose Image!!");
		imageContentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iconDialog.setVisible(true);
			}
		});
		
		iconDialog=new IconDialog(this, "Choose Image");
		
		applyButton=new Button("Apply");
		applyButton.setActionCommand("apply");
		applyButton.addActionListener(this);
		backButton=new Button("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=1;
		c.gridheight=2;
		c.weightx=0.5;
		c.weighty=0;
		c.insets=new Insets(5, 5, 5, 5);
		c.anchor=GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.NONE;
		add(nameLabel,c);
		
		c.gridx=1;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(nameTextField,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=0;
		add(nullGrids[0],c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=1;
		c.fill=GridBagConstraints.NONE;
		add(imageLabel,c);
		
		c.gridx=1;
		c.gridwidth=0;
		c.fill=GridBagConstraints.BOTH;
		add(imageContentButton,c);
		
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=0;
		add(nullGrids[1],c);
		
		c.gridx=1;
		c.gridy=14;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.EAST;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(applyButton,c);
		
		c.gridx=3;
		c.gridwidth=0;
		c.anchor=GridBagConstraints.EAST;
		add(backButton,c);
	}
	public ProfilePanel(){
		setComponents();
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	@Override 
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "profile":
			getDisplayPanel().toPanel(PanelEnum.PROFILE);
			nameTextField.setText("Name");
			nameTextField.setEditable(true);
			break;
		case "apply":
			getDisplayPanel().first();
			//profile.setName(nameTextField.getText());
			break;
		case "back":
			getDisplayPanel().first();
			break;
			
		default:
			if(e.getActionCommand().startsWith("icon")) {
				int i=Integer.valueOf(e.getActionCommand().substring("icon".length()));
				imageContentButton.setText(e.getActionCommand().toString());
				imageContentButton.setIcon(new ImageIcon("image/icon"+Integer.toString(i)+".jpg"));
				//profile.setIconID(i);
				iconDialog.dispose();
			}
			break;
		}
	}
}
