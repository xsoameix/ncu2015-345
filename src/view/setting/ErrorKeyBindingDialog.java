package view.setting;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Config;
import view.base.Button;
import view.base.Component;
import view.base.Dialog;
import view.base.Label;
import view.base.Panel;


public class ErrorKeyBindingDialog extends Dialog implements ActionListener {
	
	private Component[] nullGrids;
	private Label message;
	private Button backButton;

	private void setComponents() {
		setLayout(new GridBagLayout());
		Font font = new Font("Arial", Font.BOLD, 20);
		Insets margin = new Insets(5, 8, 5, 8);
		

		message=new Label("The control key has already existed. Please change the key.");
		message.setFont(font);
		add(message);
		
		nullGrids = new Component[1];
		for(int i = 0; i<nullGrids.length; i++) {
			nullGrids[i] = new Component();
		}
		
		backButton=new Button("back");
		backButton.setFont(font);
		backButton.setMargin(margin);
		backButton.setActionCommand(backButton.getName());
		backButton.addActionListener(this);
		add(backButton);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=0;
		c.gridheight=2;
		c.weighty=0;
		c.insets=new Insets(5, 5, 5, 5);
		c.anchor=GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(message, c);
		
		c.gridy=3;
		c.fill=GridBagConstraints.BOTH;
		add(nullGrids[0],c);
		
		c.gridx=0;
		c.gridy=5;
		c.fill=GridBagConstraints.NONE;
		add(backButton,c);
		
		pack();
	}
	
	public ErrorKeyBindingDialog(Panel panel, String name) {
		super((Frame) panel.getTopLevelAncestor(), name, false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Config.dialogDimension);
		setComponents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "back":
			dispose();
			break;
		}
	}
	

}
