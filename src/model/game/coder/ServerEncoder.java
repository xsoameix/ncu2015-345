package model.game.coder;

import java.awt.Point;
import java.util.Vector;

import model.game.Player;
import model.game.Result;
import model.game.Team;
import model.game.field.dynamic.Bullet;
import model.game.field.dynamic.Obstacle;
import model.setting.Profile;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerEncoder {

	public JSONObject removeObstacle(Obstacle obstacle) {
		return encodeObject("removeObstacle", obstacle);
	}

	public JSONObject setTotalTime(int second) {
		return encodeObject("setTotalTime", second);
	}

	public JSONObject setTime(int second) {
		return encodeObject("setTime", second);
	}

	public JSONObject setPlayerNumber(int playernumber) {
		return encodeObject("setPlayerNumber", playernumber);
	}

	public JSONObject addPlayer(Profile profile) {
		return encodeObject("addPlayer", profile);
	}

	public JSONObject removePlayer(Profile profile) {
		return encodeObject("removePlayer", profile);
	}

	public JSONObject setLocation(Player player) {
		return encodeObject("setLocation", player);
	}

	public JSONObject setMoney(Vector<Team> teams) {
		return encodeObject("setMoney", teams);
	}

	public JSONObject setKillNumber(Player player) {
		return encodeObject("setKillNumber", player);
	}

	public JSONObject addBullet(Bullet bullet) {
		return encodeObject("addBullet", bullet);
	}

	public JSONObject removeBullet(Bullet bullet) {
		return encodeObject("removeBullet", bullet);
	}

	public JSONObject updateBullet(Bullet bullet) {
		return encodeObject("updateBullet", bullet);
	}

	public JSONObject changeFlagColor(int team) {
		return encodeObject("changeFlagColor", team);
	}

	public JSONObject startGame() {
		return encodeObject("startGame", null);
	}

	public JSONObject gameOver(Result result) {
		return encodeObject("gameOver", result);
	}

	public JSONObject encodeObject(String key, Object value) {
		JSONObject object = new JSONObject();
		try {
			object.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
