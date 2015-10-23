package com.twitter.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.twitter.services.TwitterApplication;

public class TwitterFrame extends JFrame {

	private static final long serialVersionUID = -7470915094971302312L;
	public static final int FRAME_WIDTH = 1300;
	public static final int FRAME_HEIGHT = 750;
	private TwitterListPanel tlp;
	private TwitterStatusPanel tsp;
	private TwitterListFriends tlf;
	private TwitterHeaderPanel thp;

	public TwitterFrame() {
		guiFactory();

		TwitterWindowListener ec = new TwitterWindowListener(this);
		addWindowListener(ec);

		setVisible(true);
	}

	private void guiFactory() {
		setTitle("Twitter W");

		setResizable(false);

		Toolkit tk = Toolkit.getDefaultToolkit();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		initTwitterPanels();
		setLocationRelativeTo(null);
	}

	private void initTwitterPanels() {
		tlf = new TwitterListFriends(this);
		tlp = new TwitterListPanel(this);
		tsp = new TwitterStatusPanel(this);
		thp = new TwitterHeaderPanel(this);
		Color twitterThemecolor = TwitterApplication.getInstance()
				.getMyBackgroundColor();
		try {
			tlf.setBackground(twitterThemecolor);
			tlp.setBackground(twitterThemecolor);
			tsp.setBackground(twitterThemecolor);
			thp.setBackground(twitterThemecolor);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		add(tlf, BorderLayout.WEST);
		add(tlp, BorderLayout.CENTER);
		add(tsp, BorderLayout.SOUTH);
		add(thp, BorderLayout.NORTH);
	}

	public TwitterListPanel getTwitterListPanel() {
		return tlp;
	}

	public TwitterStatusPanel getTwitterStatusPanel() {
		return tsp;
	}

	public TwitterListFriends getTwitterListFriends() {
		return tlf;
	}

	public TwitterHeaderPanel getTwitterHeaderPanel() {
		return thp;
	}
}
