package view.play.game;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Config;
import view.PanelEnum;
import view.PlayPanel;
import view.base.Button;
import view.base.Dialog;

public class MenuDialog extends Dialog{
	private PlayPanel parent;
	
	private Button backButton;
	private Button leaveButton;
	private void setComponents() {
		setLayout(new FlowLayout());
		backButton=new Button("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(backButton);
		
		leaveButton=new Button("Leave");
		leaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				parent.toPanel(PanelEnum.ESTABLISH);
			}
		});
		add(leaveButton);
		
		
		//for test!
		Button tmpButton=new Button("To Result");
		tmpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				parent.toPanel(PanelEnum.RESULT);
			}
		});
		add(tmpButton);
	}
	public MenuDialog(PlayPanel parent, String name) {
		super((Frame)parent.getTopLevelAncestor(), name, false);
		this.parent=parent;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Config.dialogDimension);
		
		setComponents();
	}


}
