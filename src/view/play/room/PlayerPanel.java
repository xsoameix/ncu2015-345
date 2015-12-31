package view.play.room;

import model.game.Player;
import view.base.Panel;

public class PlayerPanel extends Panel {
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
