package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.base.Frame;

/*
 * describe the frame and add mainView
 * */
public class MainFrame extends Frame implements ActionListener{
	public MainFrame(){
		//frame initialization
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Config.frameDimension);
		
		//show frame
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="exit")
			dispose();
	}
}
