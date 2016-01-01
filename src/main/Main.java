package main;
import javax.swing.SwingUtilities;

import model.*;
import view.MainView;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainView mainView=new MainView();
				mainView.setModel(new ClientModel());
			}
		});
	}
}
