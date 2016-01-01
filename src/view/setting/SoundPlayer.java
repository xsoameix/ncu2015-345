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
		initialize();
	}
	
	public void initialize() {
		try {
			audioInputStream=AudioSystem.getAudioInputStream(new File("music/AUG-8013.wav"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	    volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volumeControl.setValue(1.0f);
		clip.start();
	}
	
	public void setVolume(float value) {
		float setting=(value>volumeControl.getMaximum()) ? volumeControl.getMaximum():value;
		volumeControl.setValue(setting);
	}
	
	public float getVolume() {
		return volumeControl.getValue();
	}
}
