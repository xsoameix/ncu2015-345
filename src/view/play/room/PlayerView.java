package view.play.room;

import javax.swing.ImageIcon;

import model.game.Player;
import view.base.Label;
import view.base.Panel;

public class PlayerView extends Panel {
	private Player player;

	private Label imageLabel;
	private Label nameLabel;
	
	public PlayerView(Player player) {
		this.player=player;
		imageLabel=new Label(new ImageIcon("src/assets/img/icon"+player.getProfile().getIconID()+".jpg"));
		nameLabel=new Label(player.getProfile().getName());
		add(imageLabel);
		add(nameLabel);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
