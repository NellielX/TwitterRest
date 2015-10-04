package com.twitter.client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

public class TwitterWindowListener implements WindowListener {

	private TwitterFrame twitterFrame;

	public TwitterWindowListener(TwitterFrame tf) {
		this.twitterFrame = tf;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		int rep = JOptionPane.showConfirmDialog(twitterFrame,
				"Veux tu quitter l'application ?");
		if (rep == 0)
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}