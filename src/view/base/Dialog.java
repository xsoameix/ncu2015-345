package view.base;

import java.awt.Frame;

import javax.swing.JDialog;


public class Dialog extends JDialog{

	public Dialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

}
