package model.game.coder;

import java.util.Iterator;

import model.Model;
import model.game.Player;

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
			switch (keys) {
			case "removeObstacle":
				//
				break;
			case "setTotalTime":
				try {
					int totalTime = object.getInt(keys);
					model.setTotalTime(totalTime);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "setTime":
				try {
					int time = object.getInt(keys);
					model.setTime(time);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "setPlayerNumber":
				//
				break;
			case "addPlayer":
				//
				break;
			case "removePlayer":
				break;
			case "setLocation":
				Player player = null;
				try {
					player = (Player) object.get(keys);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.setLocation(player);
				break;
			case "setMoney":
				//
				break;
			case "setKillNumber":
				//
				break;
			case "addBullet":
				//
				break;
			case "removeBullet":
				//
				break;
			case "updateBullet":
				//
				break;
			case "changeFlagColor":
				//
				break;
			case "startGame":
				// call ui to start game
				break;
			case "gameOver":
				//
				break;
			}
		}
	}
}
