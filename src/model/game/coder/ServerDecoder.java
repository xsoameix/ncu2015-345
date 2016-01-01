package model.game.coder;

import java.awt.Point;
import java.util.Iterator;

import model.ServerModel;

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
			String keys = (String) key.next();
			switch (keys) {
			case "requestSetTotalTime":
				try {
					int totalTime = object.getInt(keys);
					serverModel.setTotalTime(totalTime);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "requestSetPlayerNumber":
				break;
			case "requestAddPlayer":
				break;
			case "requestRemovePlayer":
				break;
			case "requestStartGame":
				break;
			case "requestSetLocation":
				Point point = null;
				try {
					point = (Point) object.get(keys);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(point.toString());
				break;
			case "requestFire":
				break;
			}
		}
	}

}
