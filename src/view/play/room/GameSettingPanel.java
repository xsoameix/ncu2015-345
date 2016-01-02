package view.play.room;

import java.awt.Component;
import java.util.HashSet;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.ClientModel;
import view.base.Label;
import view.base.Panel;

public class GameSettingPanel extends Panel{
	private ClientModel clientModel;
	private Label timeLabel;
	private Label playerNumberLabel;
	private JSlider timeSlider;
	private JSlider playerNumberSlider;
	
	public GameSettingPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		timeLabel=new Label("Time");
		playerNumberLabel=new Label("Player number");
		
		add(timeLabel);
		
		timeSlider=new JSlider(1, 9, 5);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
//		timeSlider.setLabelTable(new Hashtable());
//		for(int i=1; i<=9; i++)
//			timeSlider.getLabelTable().put(i, i);
		timeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				timeLabel.setText("Time: "+((JSlider)e.getSource()).getValue());
//				clientModel.requestSetTotalTime(((JSlider)e.getSource()).getValue()*60);
			}
		});
		add(timeSlider);
		
		add(playerNumberLabel);
		playerNumberSlider=new JSlider(1, 3, 1);
		playerNumberSlider.setPaintTicks(true);
		playerNumberSlider.setPaintLabels(true);
		playerNumberSlider.setPaintTrack(true);
//		for(int i=1; i<3; i++)
//			playerNumberSlider.getLabelTable().put(i, i*2);
		playerNumberSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				playerNumberLabel.setText("PlayerNumber: "+((JSlider)e.getSource()).getValue()*2);
//				clientModel.requestSetPlayerNumber(((JSlider)e.getSource()).getValue()*2);
			}
		});
		add(playerNumberSlider);
	}
}
