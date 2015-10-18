package com.twitter.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.net.MalformedURLException;

import com.twitter.services.TwitterApplication;

import twitter4j.TwitterException;

public class TwitterFrame extends Frame {

	private static final long serialVersionUID = -7470915094971302312L;
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

		setResizable(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setSize(1300, d.height / 2);
		initTwitterPanels();
		setLocationRelativeTo(null);		
	}

	private void initTwitterPanels() {
		tlf = new TwitterListFriends(this);
		tlp = new TwitterListPanel(this);
		tsp = new TwitterStatusPanel(this);
		thp = new TwitterHeaderPanel(this);
		
		try {
			tlf.setBackground(TwitterApplication.getInstance().getMyBackgroundColor());	
			tlp.setBackground(TwitterApplication.getInstance().getMyBackgroundColor());
			tsp.setBackground(TwitterApplication.getInstance().getMyBackgroundColor());
			thp.setBackground(TwitterApplication.getInstance().getMyBackgroundColor());
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
}
