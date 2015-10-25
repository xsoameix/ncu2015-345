package main;
import javax.swing.SwingUtilities;

import model.*;
import view.MainFrame;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame(new Application());
			}
		});
	}
}
