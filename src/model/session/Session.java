package model.session;

import model.ServerModel;

public class Session {
	public ServerModel serverModel;
	private int id;

	public Session(ServerModel model) {
		this.serverModel = model;
	}

	public void onData(byte body[]) {
		serverModel.set(body);
	}
}
