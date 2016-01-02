package view.setting;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
	private AudioInputStream audioInputStream;
	private Clip clip;
	FloatControl volumeControl;
	
	public SoundPlayer() {
	}
	
	public void initialize(String filename) {
		try {
			audioInputStream=AudioSystem.getAudioInputStream(new File(filename));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	    volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volumeControl.setValue(1.5f);
		clip.start();
	}
	
	public void setVolume(float value) {
		volumeControl.setValue(value);
	}
	
	public float getVolume() {
		return volumeControl.getValue();
	}
	
	
	public boolean limit(float value) {
		float maximum=6;
		float minimum=-6;
		
		if(value>minimum && value < maximum)
			return true;
		return false;
	}
}
