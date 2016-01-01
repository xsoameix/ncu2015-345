package view.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import view.base.extend.DisplayPanel;

public abstract class Panel extends JPanel implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public DisplayPanel getDisplayPanel(){
		return (DisplayPanel)getParent();
	}
	
	//listener: the panel to display will add listener here
	private EventListenerList listenerList=new EventListenerList();
	public void addActionListener(ActionListener actionListener){
		listenerList.add(ActionListener.class, actionListener);
	}
	public void fireActionEvent(ActionEvent e){
		for(ActionListener actionListener: listenerList.getListeners(ActionListener.class))
			actionListener.actionPerformed(e);
	}

}
