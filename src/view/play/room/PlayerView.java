package view.play.room;

import model.game.Player;
import view.base.Panel;

public class PlayerView extends Panel {
	private Player player;

	public PlayerView(Player player) {
		this.player=player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
