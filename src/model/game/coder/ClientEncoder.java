package model.game.coder;

import java.awt.Point;

import org.json.JSONException;
import org.json.JSONObject;

import model.game.Player;

public class ClientEncoder {
	//in room
	public JSONObject requestSetGameTime(int totalTime){
		return encodeObject("requestSetGameTime", totalTime);
	}
	public JSONObject requestSetPlayerNumber(int playerNumber){
		return encodeObject("requestSetPlayerNumber", playerNumber);
	}
	public JSONObject requestAddPlayer(Player player){
		return encodeObject("requestAddPlayer", player);
	}
	public JSONObject requestRemovePlayer(Player player){
		return encodeObject("requestRemovePlayer", player);
	}
	public JSONObject requestStartGame(){
		return encodeObject("requestStartGame", null);
	}
	public JSONObject requestSetLocation(Point point){
		return encodeObject("requestSetLocation", point);
	}
	public JSONObject requestFire(){
		return encodeObject("requestFire", null);
	}
	public JSONObject encodeObject(String key, Object value){
		JSONObject object=new JSONObject();
		try {
			object.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
