package model.game.coder;

import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.ServerModel;
import model.game.Player;

public class ServerDecoder {
	private ServerModel serverModel;
	private Player player;

	public ServerDecoder(ServerModel serverModel) {
		// TODO Auto-generated constructor stub
		this.serverModel = serverModel;
	}

	public void decode(int id, JSONObject object) {
		Gson gson = new Gson();
		Iterator<?> key = object.keys();
		try {
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
						player = gson.fromJson(object.get(keys).toString(), Player.class);
						assert player != null : "[ServerDecoder] decode Player is null";
						player.setID(id);
						serverModel.addPlayer(player);
						break;
					case "requestRemovePlayer":
						player = gson.fromJson(object.get(keys).toString(), Player.class);
						assert player != null : "[ServerDecoder] decode Player is null";
						player.setID(id);
						serverModel.removePalyer(player);
						break;
					case "requestStartGame":
						serverModel.startGame();
						break;
					case "requestSetLocation":
						Point point = gson.fromJson(object.get(keys).toString(), Point.class);
						serverModel.setLocation(id, point);
						break;
					case "requestFire":
						serverModel.fire(id);
						break;
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
