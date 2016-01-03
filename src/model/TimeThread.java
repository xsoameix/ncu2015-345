package model;

public class TimeThread extends Thread{
	private Game game;

	public TimeThread(Game game){
		this.game = game;
	}
	
	@Override
	public void run(){
		while(true){
			synchronized(this){
				game.setTime(game.getTime()-1);
			}
			try {
				TimeThread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
