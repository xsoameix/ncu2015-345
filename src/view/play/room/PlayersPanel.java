package view.play.room;

import java.awt.GridLayout;

import model.game.Player;
import model.game.field.dynamic.Character;
import model.setting.Profile;
import view.base.Panel;

public class PlayersPanel extends Panel {
	public PlayersPanel(){
		setLayout(new GridLayout(0,2));
		for(int i=0; i<6; i++)
			add(new PlayerView(new Player(new Character(), new Profile("", 0))));
	}
	public void addPlayer(Player player) {
		((PlayerView)getComponent(player.getID()-1)).setPlayer(player);
	}
}
