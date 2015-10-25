package model.network;

import controller.PlayerController;

public class Client {
	private PlayerController playerController;

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
}
