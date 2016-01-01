package view.play.game;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;

import model.game.Player;
import view.base.Label;
import view.base.Panel;

public class PersonalPanel extends Panel {
	private Player player;
	//kill, death...
	private Label killIcon;
	private Label deathIcon;
	private Label killLabel;
	private Label deathLabel;
	
	public PersonalPanel(){
		setComponents();
	}

	private void setComponents() {
		setLayout(new GridLayout(2, 2));
		
		killIcon=new Label(new ImageIcon("src/assets/img/kill.jpg"));
		deathIcon=new Label(new ImageIcon("src/assets/img/death.jpg"));
		killLabel=new Label("0");
		deathLabel=new Label("0");
		
		add(killIcon);
		add(killLabel);
		add(deathIcon);
		add(deathLabel);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
//		deathLabel.setText(String.valueOf(player.getDeath()));
//		killLabel.setText(String.valueOf(player.getKill()));
	}
}
