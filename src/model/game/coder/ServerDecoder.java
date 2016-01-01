package model.game.coder;

import java.awt.Point;
import java.util.Iterator;

import model.ServerModel;
import model.game.Player;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerDecoder {
	private ServerModel serverModel;

	public ServerDecoder(ServerModel serverModel) {
		// TODO Auto-generated constructor stub
		this.serverModel = serverModel;
	}

	public void decode(JSONObject object) {
		Iterator<?> key = object.keys();
		while (key.hasNext()) {
			try {
				String keys = (String) key.next();
				switch (keys) {
				case "requestSetTotalTime":
					int totalTime = object.getInt(keys);
					serverModel.setTotalTime(totalTime);
					break;
				case "requestSetPlayerNumber":
					int playerNumber = object.getInt(keys);
					serverModel.setPlayerNumber(playerNumber);
					break;
				case "requestAddPlayer":
					Player player = (Player) object.get(keys);
					serverModel.addPlayer(player);
					break;
				case "requestRemovePlayer":
					player = (Player) object.get(keys);
					serverModel.removePalyer(player);
					break;
				case "requestStartGame":
					serverModel.startGame();
					break;
				case "requestSetLocation":
					Point point = null;
					point = (Point) object.get(keys);
					serverModel.setLocation(point);
					break;
				case "requestFire":
					serverModel.fire();
					break;
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
