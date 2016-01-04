package view.setting;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import model.setting.Profile;
import view.PanelEnum;
import view.base.Button;
import view.base.Component;
import view.base.Label;
import view.base.Panel;
import view.base.TextField;
import view.base.extend.AbstractView;

public class ProfilePanel extends AbstractView {
	private Profile newProfile;
	
	private Component[] nullGrids;
	private Label nameLabel;
	private TextField nameTextField;
	private Label imageLabel;
	private Button imageContentButton;
	private IconDialog iconDialog;
	private Button applyButton;
	
	private void setComponents(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 100, 200, 100));
		Font font = new Font("Arial", Font.BOLD, 20);
		Insets margin = new Insets(8, 10, 8, 10);
		
		nullGrids = new Component[2];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		nameLabel=new Label("Your Name:");
		nameLabel.setFont(font);
		nameTextField=new TextField();
		nameTextField.setFont(font);
		nameTextField.setMargin(margin);
		imageLabel=new Label("Your Image:");
		imageLabel.setFont(font);
		imageContentButton=new Button("Choose Image!!");
		imageContentButton.setFont(font);
		imageContentButton.setMargin(margin);
		imageContentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iconDialog.setVisible(true);
			}
		});
		
		iconDialog=new IconDialog(this, "Choose Image");
		
		applyButton=new Button("Apply");
		applyButton.setFont(font);
		applyButton.setMargin(margin);
		applyButton.setActionCommand("apply");
		applyButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=1;
		c.gridheight=2;
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
		c.gridwidth=0;
		c.anchor=GridBagConstraints.EAST;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(applyButton,c);
	}
	public ProfilePanel(){
		setComponents();
		newProfile = new Profile();
	}
	
	@Override 
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "profile":
			getDisplayPanel().toPanel(PanelEnum.PROFILE);
			nameTextField.setText(newProfile.getName());
			nameTextField.setEditable(true);
			break;
		case "apply":
			getDisplayPanel().first();
			newProfile.setName(nameTextField.getText());
			clientModel.getSetting().setProfile(newProfile);
			break;
			
		default:
			if(e.getActionCommand().startsWith("icon")) {
				int i=Integer.valueOf(e.getActionCommand().substring("icon".length()));
				imageContentButton.setText("");
				imageContentButton.setIcon(new ImageIcon("src/assets/img/icon"+Integer.toString(i)+".jpg"));
				imageContentButton.setLayout(new GridLayout(2, 1, 0, 0));
				newProfile.setIconID(i);
				iconDialog.dispose();
			}
			break;
		}
	}
}
