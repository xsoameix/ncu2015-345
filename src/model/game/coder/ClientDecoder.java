package model.game.coder;

import java.util.Iterator;
import java.util.Vector;

import model.Model;
import model.game.Player;
import model.game.Result;
import model.game.Team;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Obstacle;

import org.json.JSONException;
import org.json.JSONObject;

public class ClientDecoder {
	// private AbstractModel model;
	private Model model;

	public ClientDecoder(Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	public void decode(JSONObject object) {
		Iterator<?> key = object.keys();
		while (key.hasNext()) {
			String keys = (String) key.next();
			try {
				switch (keys) {
				case "removeObstacle":
					Obstacle obstacle = (Obstacle) object.get(keys);
					model.removeObstacle(obstacle);
					break;
				case "setTotalTime":
					int totalTime = object.getInt(keys);
					model.setTotalTime(totalTime);
					break;
				case "setTime":
					int time = object.getInt(keys);
					model.setTime(time);
					break;
				case "setPlayerNumber":
					int playerNumber = object.getInt(keys);
					model.setPlayerNumber(playerNumber);
					break;
				case "addPlayer":
					Player player = (Player) object.get(keys);
					model.addPlayer(player);
					break;
				case "removePlayer":
					player = null;
					player = (Player) object.get(keys);
					model.removePlayer(player);
					break;
				case "setLocation":
					player = null;
					player = (Player) object.get(keys);
					model.setLocation(player);
					break;
				case "setMoney":
					Vector<Team> teams = (Vector<Team>) object.get(keys);
					model.setMoney(teams);
					break;
				case "setKillNumber":
					player = null;
					player = (Player) object.get(keys);
					model.setKillNumber(player);
					break;
				case "addBullet":
					Bullet bullet = (Bullet) object.get(keys);
					model.addBullet(bullet);
					break;
				case "removeBullet":
					bullet = null;
					bullet = (Bullet) object.get(keys);
					model.removeBullet(bullet);
					break;
				case "updateBullet":
					bullet = null;
					bullet = (Bullet) object.get(keys);
					model.updateBullet(bullet);
					break;
				case "changeFlagColor":
					//
					break;
				case "startGame":
					model.startGame();
					break;
				case "gameOver":
					Result result = (Result) object.get(keys);
					model.gameOver(result);
					break;
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
