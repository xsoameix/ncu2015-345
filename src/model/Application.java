package model;

public class Application {
	private Setting setting;
	private Room room;
	
	
	public void establishRoom() {
		room=new Room();
	}
	public Room getRoom() {
		return room;
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
}
