package com.twitter.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class TwitterFrame extends Frame {

	private static final long serialVersionUID = -7470915094971302312L;
	private TwitterListPanel tlp;
	private TwitterStatusPanel tsp;

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
		setSize(500, d.height / 2);
		initTwitterPanels();
		setLocationRelativeTo(null);

	}

	private void initTwitterPanels() {
		tlp = new TwitterListPanel(this);
		tsp = new TwitterStatusPanel(this);
		add(tlp, BorderLayout.NORTH);
		add(tsp, BorderLayout.SOUTH);
	}

	public TwitterListPanel getTwitterListPanel() {
		return tlp;
	}

	public TwitterStatusPanel getTwitterStatusPanel() {
		return tsp;
	}
}
