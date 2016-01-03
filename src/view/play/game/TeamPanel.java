package view.play.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

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
		setLayout(new GridLayout());
		moneyIcon=new Label(new ImageIcon("src/assets/img/money.jpg"));
		turfIcon=new Label(new ImageIcon("src/assets/img/turf.jpg"));
		moneyLabel=new Label("0");
		turfLabel=new Label("0");
		
		add(moneyIcon);
		add(moneyLabel);
		add(turfIcon);
		add(turfLabel);
		

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		assert team!=null:"null team";
		moneyLabel.setText(String.valueOf(team.getMoney()));
		turfLabel.setText(String.valueOf(team.getTurfNumber()));
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
		for(Component component: getComponents()){
			((JComponent)component).setBorder(BorderFactory.createLineBorder(team.getID()==1?Color.BLUE:Color.RED));
		}
	}
}
