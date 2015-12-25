package model.game.coder;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;

public class ClientDecoder {
//	private AbstractModel model;
	
	public void decode(JSONObject object){
		for(String key: object.keySet()){
//			try {
//				Method method=model.getClass().getMethod(key, new Class[]{Object.class});
//				method.invoke(model, object.get(key));
//			} catch (NoSuchMethodException | SecurityException | JSONException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
			
			switch(key){
			case "requestSetTotalTime":
				int totalTime=object.getInt(key);
				break;
			case "requestSetTime":
				int time=object.getInt(key);
				break;
			case "requestAddPlayer":
				break;
			case "requestRemovePlayer":
				break;
			case "requestStartGame":
				break;
			case "requestSetLocation":
				Point point=(Point) object.get(key);
				System.out.println(point.toString());
				break;
			case "requestFire":
				break;
			}
		}
	}
}
