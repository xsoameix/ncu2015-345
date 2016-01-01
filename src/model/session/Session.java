package model.session;

import model.ServerModel;

public class Session {
	public ServerModel model;
	private int id;

	public Session(ServerModel model) {
		this.model = model;
	}

	public void onData(byte body[]) {
		model.set(body);
	}
}
