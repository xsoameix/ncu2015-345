package model;

import view.play.PlayPanel;

public class ClientModel {

	private Model model;

	public ClientModel() {
		model = new Model();
	}

	/* for outside API Start */

	public void requestFire() {
		model.requestFire();
	}

	public void requestStartGame() {
		model.requestStartGame();
	}

	public void requestSetTotalTime(int time) {
		model.requestSetTotalTime(time);
	}

	public void requestSetPlayerNumber(int number) {
		model.requestSetPlayerNumber(number);
	}

	public void requestSetLocation(int x, int y) {
		model.requestSetLocation(x, y);
	}

	// host
	public Boolean requestEstablishRoom(int port) {
		return model.requestEstablishRoom(port);
	}

	// client
	public Boolean requestEnterRoom(String ip, int port) {
		return model.requestEnterRoom(ip, port);
	}

	/* for outside API end */

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setPlayPanel(PlayPanel playPanel){
		model.setPlayPanel(playPanel);
	}
}
