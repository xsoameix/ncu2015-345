package model;

public class StubModel extends Model {
	boolean requestFire = false;
	boolean requestStartGame = false;
	boolean requestSetTotalTime = false;
	boolean requestSetPlayerNumber = false;
	boolean requestSetLocation = false;
	boolean requestKeyInput = false;
	boolean set = false;

	protected boolean requestEstablishRoom(int port) {
		return true;
	}

	protected boolean requestEnterRoom(String ip, int port) {
		return true;
	}

	protected void requestFire() {
		requestFire = true;
	}

	protected void requestStartGame() {
		requestStartGame = true;
	}

	protected void requestSetTotalTime(int time) {
		requestSetTotalTime = true;
	}

	protected void requestSetPlayerNumber(int time) {
		requestSetPlayerNumber = true;
	}

	protected void requestSetLocation(int x, int y) {
		requestSetLocation = true;
	}
	
	public void set(Byte[] packet) {
		set = true;
	}
}
