package com.twitter.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.twitter.services.TwitterApplication;

public class TwitterHeaderEntierPanel extends JPanel {

	private static final long serialVersionUID = -7470915094971302312L;
	public static final int FRAME_WIDTH = 1300;
	public static final int FRAME_HEIGHT = 300;

	private TwitterFrame tf;
	private TwitterHeaderPanel thp;
	private TwitterBannierePanel tbp;
	
	public TwitterHeaderEntierPanel(TwitterFrame tf) {
		this.tf = tf;
		setPreferredSize(new Dimension(TwitterFrame.FRAME_WIDTH, FRAME_HEIGHT));
		initTwitterPanels();
	}
	
	private void initTwitterPanels() {
		
		thp = new TwitterHeaderPanel(tf);
		tbp = new TwitterBannierePanel(tf);
		
		Color twitterThemecolor = TwitterApplication.getInstance().getMyBackgroundColor();
		
		try {
			thp.setBackground(twitterThemecolor);
			tbp.setBackground(twitterThemecolor);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		add(tbp, BorderLayout.NORTH);
		add(thp, BorderLayout.SOUTH);			
	}
	
	public TwitterHeaderPanel getTwitterHeaderPanel() {
		return thp;
	}
	
	public TwitterBannierePanel getTwitterBannierePanel() {
		return tbp;
	}
	
}