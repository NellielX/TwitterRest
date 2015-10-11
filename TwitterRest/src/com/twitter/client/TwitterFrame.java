package com.twitter.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class TwitterFrame extends Frame {

	private static final long serialVersionUID = -7470915094971302312L;
	private TwitterListPanel tlp;
	private TwitterStatusPanel tsp;
	private TwitterListFriends tlf;

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
		add(tlf, BorderLayout.WEST);
		add(tlp, BorderLayout.CENTER);
		add(tsp, BorderLayout.SOUTH);
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
