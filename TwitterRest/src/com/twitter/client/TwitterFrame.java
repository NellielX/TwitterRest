package com.twitter.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.twitter.services.TwitterApplication;

public class TwitterFrame extends JFrame {

	private static final long serialVersionUID = -7470915094971302312L;
	public static final int FRAME_WIDTH = 1300;
	public static final int FRAME_HEIGHT = 800;
	private TwitterListPanel tlp;
	private TwitterStatusPanel tsp;
	private TwitterListFriends tlf;
	private TwitterHeaderPanel thp;
	private TwitterBannierePanel tbp;
	private TwitterHeaderEntierPanel thep;

	public TwitterFrame() {
		guiFactory();

		TwitterWindowListener ec = new TwitterWindowListener(this);
		addWindowListener(ec);

		setVisible(true);
	}

	private void guiFactory() {
		setTitle("Twitter W");

		setResizable(false);

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		initTwitterPanels();
		setLocationRelativeTo(null);
	}

	private void initTwitterPanels() {
		tlf = new TwitterListFriends(this);
		tlp = new TwitterListPanel(this);
		tsp = new TwitterStatusPanel(this);
		thep = new TwitterHeaderEntierPanel(this);
		
		Color twitterThemecolor = TwitterApplication.getInstance()
				.getMyBackgroundColor();
		try {
			tlf.setBackground(twitterThemecolor);
			tlp.setBackground(twitterThemecolor);
			tsp.setBackground(twitterThemecolor);
			thep.setBackground(twitterThemecolor);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		add(tlf, BorderLayout.WEST);
		add(tlp, BorderLayout.CENTER);
		add(tsp, BorderLayout.SOUTH);
		add(thep, BorderLayout.NORTH);
			
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
	
	public TwitterBannierePanel getTwitterBannierePanel() {
		return tbp;
	}
	
	public TwitterHeaderEntierPanel getTwitterHeaderEntierPanel() {
		return thep;
	}
}
