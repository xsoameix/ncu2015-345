package view.play;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import view.PanelEnum;
import view.PlayPanel;
import view.base.*;

public class EstablishPanel extends Panel{
	private PlayPanel parent;
	
	private Label IPLabel;
	private Label portLabel;
	private TextField IPTextField;
	private TextField portTextField;
	
	private Button establishButton;
	private Button backButton;
	
	private void setComponents(){
		//this is temporary setting!
		GridLayout gridLayout=new GridLayout(3,2,100,100);
		setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		setLayout(gridLayout);
		//
		
		IPLabel=new Label("IP");
		portLabel=new Label("Port");
		IPTextField=new TextField();
		portTextField=new TextField();
		add(IPLabel);
		add(IPTextField);
		add(portLabel);
		add(portTextField);
		
		//establish
		establishButton=new Button("Establish");
		establishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.toPanel(PanelEnum.ROOM);
				parent.establishRoom();
			}
		});
		add(establishButton);
		
		//back
		backButton=new Button("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.back();
			}
		});
		add(backButton);
	}
	
	public EstablishPanel(PlayPanel panel){
		this.parent=panel;
		setComponents();
	}

	public void setHost(boolean isHost) {
//		this.isHost = isHost;
		IPTextField.setEnabled(!isHost);
	}

}
