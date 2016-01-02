package model.game.coder;

import java.util.Iterator;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.ClientModel;
import model.Room;
import model.game.Player;
import model.game.Result;
import model.game.Team;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Obstacle;
import model.game.field.dynamic.Turf;

public class ClientDecoder {
	// private AbstractModel model;
	private ClientModel model;
	private Player player;
	private Bullet bullet;

	public ClientDecoder(ClientModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	public void decode(JSONObject object) {
		Gson gson = new Gson();
		Iterator<?> key = object.keys();
		while (key.hasNext()) {
			String keys = (String) key.next();
			try {
				switch (keys) {
				case "removeObstacle":
					Obstacle obstacle = gson.fromJson(object.get(keys).toString(), Obstacle.class);
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
					Room room = gson.fromJson(object.get(keys).toString(), Room.class);
					model.addPlayer(room);
					break;
				case "removePlayer":
					player = gson.fromJson(object.get(keys).toString(), Player.class);
					model.removePlayer(player);
					break;
				case "setLocation":
					player = gson.fromJson(object.get(keys).toString(), Player.class);
					model.setLocation(player);
					break;
				case "setMoney":
					Vector<Team> teams = gson.fromJson(object.get(keys).toString(), new TypeToken<Vector<Team>>() {
					}.getType());
					model.setMoney(teams);
					break;
				case "setKillNumber":
					player = gson.fromJson(object.get(keys).toString(), Player.class);
					model.setKillNumber(player);
					break;
				case "addBullet":
					bullet = gson.fromJson(object.get(keys).toString(), Bullet.class);
					model.addBullet(bullet);
					break;
				case "removeBullet":
					bullet = gson.fromJson(object.get(keys).toString(), Bullet.class);
					model.removeBullet(bullet);
					break;
				case "updateBullet":
					bullet = gson.fromJson(object.get(keys).toString(), Bullet.class);
					model.updateBullet(bullet);
					break;
				case "changeTurfColor":
					Turf turf = gson.fromJson(object.get(keys).toString(), Turf.class);
					model.changeTurfColor(turf);
					break;
				case "startGame":
					model.startGame();
					break;
				case "gameOver":
					Result result = gson.fromJson(object.get(keys).toString(), Result.class);
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
