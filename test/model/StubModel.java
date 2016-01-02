package model;

public class StubModel extends ClientModel {
	boolean requestFire = false;
	boolean requestStartGame = false;
	boolean requestSetTotalTime = false;
	boolean requestSetPlayerNumber = false;
	boolean requestSetLocation = false;
	boolean requestKeyInput = false;

	public boolean requestEstablishRoom(int port) {
		return true;
	}

	public boolean requestEnterRoom(String ip, int port) {
		return true;
	}

	public void requestFire() {
		super.requestFire();
		requestFire = true;
	}

	public void requestStartGame() {
		requestStartGame = true;
	}

	public void requestSetTotalTime(int time) {
		requestSetTotalTime = true;
	}

	public void requestSetPlayerNumber(int time) {
		requestSetPlayerNumber = true;
	}

	public void requestSetLocation(int x, int y) {
		requestSetLocation = true;
	}
}
