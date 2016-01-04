package view.play.room;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import model.game.Player;
import view.base.Label;
import view.base.Panel;

public class PlayerView extends Panel {
	private Player player;
	private Label imageLabel;
	private Label nameLabel;
	
	public PlayerView(Player player) {
		setLayout(new GridLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		this.player=player;
		imageLabel=new Label();
		nameLabel=new Label();
		nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
		add(imageLabel);
		add(nameLabel);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		imageLabel.setIcon(new ImageIcon("src/assets/img/icon"+player.getProfile().getIconID()+".jpg"));
		nameLabel.setText(player.getProfile().getName());
		setBorder(BorderFactory.createLineBorder(player.getID()%2==0?Color.RED:Color.BLUE, 5));
	}
}
