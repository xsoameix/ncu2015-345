package model.setting;

public class Profile {
	private String name;
	private int iconID;
	
	public Profile(String name, int iconID){
		this.name = name;
		this.iconID = iconID;
	}
	
	public Profile(){
		name = "default";
		iconID = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIconID(int id){
		this.iconID = id;
	}
	
	public int getIconID(){
		return iconID;
	}
	
}
