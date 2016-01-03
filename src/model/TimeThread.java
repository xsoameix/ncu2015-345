package model;

import java.io.IOException;

import model.game.Result;
import model.game.Team;

public class TimeThread extends Thread {
	private ServerModel serverModel;
	private boolean leave = false;

	public TimeThread(ServerModel serverModel) {
		this.serverModel = serverModel;
	}

	@Override
	public void run() {
		while (!leave) {
			synchronized (this) {
				try {
					int time = serverModel.getGame().getTime() - 1;
					if (time >= 0) {
						serverModel.setTime(time);
						serverModel.getGame().setTime(time);
					} else {
						serverModel.gameOver(new Result(serverModel.getGame().getTeams()));
						leave = true;
					}

					for (int i = 0; i < serverModel.getGame().getTeams().size(); i++) {
						Team tmp = serverModel.getGame().getTeams().get(i);
						tmp.setMoney(tmp.getMoney() + tmp.getTurfNumber() * 300);
					}

					serverModel.setMoney(serverModel.getGame().getTeams());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				TimeThread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
