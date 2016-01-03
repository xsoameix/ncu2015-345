package view.play;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import view.PanelEnum;
import view.base.Button;
import view.base.Label;
import view.base.TextField;
import view.base.extend.AbstractView;

public class EstablishPanel extends AbstractView{
	private Label IPLabel;
	private Label portLabel;
	private TextField IPTextField;
	private TextField portTextField;
	private Button buttons[];
	
	private boolean isHost;
	
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
		
		buttons=new Button[]{
			new Button("establish"),
			new Button("back")		
		};
		for(Button button: buttons){
			button.setActionCommand(button.getName());
			button.addActionListener(this);
			add(button);
		}
	}
	
	public EstablishPanel(){
		setComponents();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		//from parent
		case "host":
			isHost=true;
			getDisplayPanel().toPanel(PanelEnum.ESTABLISH);
			try {
				IPTextField.setText(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			IPTextField.setEditable(false);
			break;
		case "client":
			isHost=false;
			getDisplayPanel().toPanel(PanelEnum.ESTABLISH);
			IPTextField.setText("");
			IPTextField.setEditable(true);
			break;
			
		//button
		case "establish":
			if(isHost){
				clientModel.requestEstablishRoom(Integer.valueOf(portTextField.getText()));
			}
			else {
				if(clientModel.requestEnterRoom(IPTextField.getText(), Integer.valueOf(portTextField.getText())))
					getDisplayPanel().next();
				else
					new JOptionPane("Connect timeout! Game may be already started.");
			}
			break;
		case "enter":
			getDisplayPanel().next();
			break;
		case "back":
			getDisplayPanel().back();
			break;
		}
	}
}
