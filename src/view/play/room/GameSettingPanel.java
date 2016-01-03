package view.play.room;

import javax.swing.BoxLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.base.Label;
import view.base.extend.AbstractView;

public class GameSettingPanel extends AbstractView{
	private Label timeLabel;
	private Label playerNumberLabel;
	private JSlider timeSlider;
	private JSlider playerNumberSlider;
	
	public GameSettingPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		timeLabel=new Label("Time: 5");
		playerNumberLabel=new Label("Player number: 2");
		
		add(timeLabel);
		
		timeSlider=new JSlider(1, 9, 5);
//		timeSlider.setPaintTicks(true);
//		timeSlider.setPaintLabels(true);
		timeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				timeLabel.setText("Time: "+((JSlider)e.getSource()).getValue());
				clientModel.requestSetTotalTime(((JSlider)e.getSource()).getValue()*60);
			}
		});
		add(timeSlider);
		
		add(playerNumberLabel);
		playerNumberSlider=new JSlider(1, 3, 1);
//		playerNumberSlider.setPaintTicks(true);
//		playerNumberSlider.setPaintLabels(true);
//		playerNumberSlider.setPaintTrack(true);
		playerNumberSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				playerNumberLabel.setText("PlayerNumber: "+((JSlider)e.getSource()).getValue()*2);
				clientModel.requestSetPlayerNumber(((JSlider)e.getSource()).getValue()*2);
			}
		});
		add(playerNumberSlider);
	}
}
