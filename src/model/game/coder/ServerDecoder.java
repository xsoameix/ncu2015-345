package model.game.coder;

import java.awt.Point;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerDecoder {
	public void decode(JSONObject object){
		Iterator<?> key = object.keys();
		while(key.hasNext()){
//			try {
//				Method method=model.getClass().getMethod(key, new Class[]{Object.class});
//				method.invoke(model, object.get(key));
//			} catch (NoSuchMethodException | SecurityException | JSONException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
			String keys = (String) key.next();
		switch(keys){
			case "requestSetTotalTime":
			try {
				int totalTime = object.getInt(keys);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				break;
				
			case "requestSetTime":
			try {
				int time = object.getInt(keys);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
				point = (Point)object.get(keys);
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
	


