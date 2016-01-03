package view.play.game;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import model.game.Team;
import view.base.Label;
import view.base.Panel;

public class TeamPanel extends Panel{
	private Team team;
	//money, turf
	private Label moneyIcon;
	private Label turfIcon;
	private Label moneyLabel;
	private Label turfLabel;
	
	public TeamPanel() {
		setComponents();
	}

	private void setComponents() {
		moneyIcon=new Label(new ImageIcon("src/assets/img/money.jpg"));
		turfIcon=new Label(new ImageIcon("src/assets/img/turf.jpg"));
		moneyLabel=new Label("0");
		turfLabel=new Label("0");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		moneyLabel.setText(String.valueOf(team.getMoney()));
//		turfLabel.setText(String.valueOf(team.getTurfNumber()));
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
