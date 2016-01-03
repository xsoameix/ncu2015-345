package view.setting;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import view.Config;
import view.base.Button;
import view.base.Dialog;
import view.base.Panel;

public class IconDialog extends Dialog implements ActionListener {
	private int iconID;
	private Panel parent;
	private Button backButton;
	private Button[] icons;
	private void setComponents() {
		setLayout(new FlowLayout());
		Font font = new Font("Arial", Font.BOLD, 20);
		Insets margin = new Insets(5, 8, 5, 8);
		
		icons=new Button[6];
		for(int i=0; i<icons.length; i++){
			icons[i]=new Button();
			icons[i].setIcon(new ImageIcon("image/icon"+i+".jpg"));
			icons[i].setActionCommand("icon"+i);
			icons[i].addActionListener(parent);
			add(icons[i]);
		}
		
		backButton=new Button("back");
		backButton.setActionCommand(backButton.getName());
		backButton.setFont(font);
		backButton.setMargin(margin);
		backButton.addActionListener(this);
		add(backButton);
		
		pack();
	}
	
	public IconDialog(Panel panel, String name) {
		super((Frame) panel.getTopLevelAncestor(), name, false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Config.dialogDimension);
		parent=panel;
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
	
	public int getIconID() {
		return iconID;
	}
}
