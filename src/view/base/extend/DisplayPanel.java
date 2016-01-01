package view.base.extend;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PanelEnum;
import view.base.Panel;

public class DisplayPanel extends AbstractView implements ActionListener{
	private CardLayout cardLayout;
	
	public DisplayPanel(){
		cardLayout=new CardLayout();
		setLayout(cardLayout);
	}
	
	public void add(Panel panel, PanelEnum panelEnum){
		super.add(panel, panelEnum.toString());
	}
	public void add(DisplayPanel displayPanel, PanelEnum panelEnum){
		super.add(displayPanel, panelEnum.toString());
		addActionListener(displayPanel);
	}
	public void toPanel(PanelEnum panelEnum){
		cardLayout.show(this, panelEnum.toString());
	}
	public void back(){
		((DisplayPanel)getParent()).cardLayout.first((DisplayPanel)getParent());
	}
	public void first() {
		cardLayout.first(this);
	}
	public void next(){
		cardLayout.next(this);
	}
	public void previous(){
		cardLayout.previous(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}



}
