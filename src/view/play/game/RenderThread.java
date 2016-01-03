package view.play.game;

import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

public class RenderThread {
	private Component component;
	private Timer timer;
	private static final int DELAY=10;
	
	public RenderThread(Component component){
		this.component=component;
		timer=new Timer();
	}
	public void start(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				component.repaint();
			}
		}, 0, DELAY);
	}
	public void end(){
		timer.cancel();
	}
}
