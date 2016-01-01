package model;

import model.setting.*;

/**
 * local setting: volume key network setting: profile: name image
 */
public class Setting {
	private Profile profile;
	private KeyBinding keyBinding;
	private int volume = 0;

	public Setting() {
		profile = new Profile();
		keyBinding = new KeyBinding();
	}

	public void loadSetting() {

	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public KeyBinding getKeyBinding() {
		return keyBinding;
	}

	public void setKeyBinding(KeyBinding keyBinding) {
		this.keyBinding = keyBinding;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}
