package model;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import model.game.Player;
import model.game.coder.ServerDecoder;
import model.game.coder.ServerEncoder;
import model.setting.Profile;
import model.game.field.dynamic.Character;

public class ServerModel {
	private Room room;
	private Profile profile;
	private Game game;
	private Player player; 
	private Character character;
	private ServerDecoder Decoder;
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room){
		this.room = room;
	}
	
	public void setTotalTime(int second){
		game.setTime(second);
	}
	
	public void setPlayerNumber(int playernumber){
		room.setPlayerNumber(playernumber);
	}
	
	public boolean startGame(){
		return true;
	}
	
	public boolean addPlayer(Player player){
		room.addPlayer(player);
		return true;
	}
	
	public boolean removePalyer(Player player){
		room.removePlayer(player);
		return true;
	}
	
	
	//��ûд
	public boolean setLocation(int x,int y){
		Point location=null;
		location.x=x;
		location.y=y;
		character.setLocation(location);
		return true;
	}	
	
	//��ûд
	public boolean fire(){
		//character.newbullet();
		return true;
	}
	
	public void set(Byte[] packet){
		  JSONObject jsonObj = null;
		    byte[] str = packet.toString().getBytes(StandardCharsets.UTF_8);
			try {
				String content = new String(str, StandardCharsets.UTF_8);
				jsonObj = new JSONObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Decoder.decode(jsonObj);
	}
	
}