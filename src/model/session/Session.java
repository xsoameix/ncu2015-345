package model.session;

import model.ServerModel;

public class Session {
	public ServerModel serverModel;
	private int id;

	public Session(ServerModel model) {
		this.serverModel = model;
		id = serverModel.getSessionID();
		System.out.println("Session id " + id);
	}

	public void onData(byte body[]) {
		serverModel.set(id, body);
	}
}
