package view.play.game;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Config;
import view.PlayPanel;
import view.base.Button;
import view.base.Dialog;
import view.base.Panel;
import view.play.GamePanel;

public class MenuDialog extends Dialog implements ActionListener{
	private Panel parent;
	private Button backButton;
	private Button leaveButton;
	private void setComponents() {
		setLayout(new FlowLayout());
		backButton=new Button("back");
		backButton.setActionCommand(backButton.getName());
		backButton.addActionListener(parent);
		add(backButton);
		
		leaveButton=new Button("leave");
		leaveButton.setActionCommand(backButton.getName());
		leaveButton.addActionListener(parent);
		add(leaveButton);
		
		//for test!
		Button tmpButton=new Button("result");
		tmpButton.setActionCommand(backButton.getName());
		tmpButton.addActionListener(parent);
		add(tmpButton);
	}
	public MenuDialog(Panel panel, String name) {
		super((Frame) panel.getTopLevelAncestor(), name, false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Config.dialogDimension);
		parent=panel;
		setComponents();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		parent.fireActionEvent(e);
	}


}
