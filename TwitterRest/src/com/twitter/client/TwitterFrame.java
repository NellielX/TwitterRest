package com.twitter.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TwitterFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7470915094971302312L;

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
		Dimension d = tk.getScreenSize();
		setSize(500, d.height / 2);
		initTwitterPanels();
		setLocationRelativeTo(null);

	}

	private void initTwitterPanels() {
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.green);
		JTextArea txtAreaList = new JTextArea();

		textPanel.add(txtAreaList);

		TwitterStatusPanel tsp = new TwitterStatusPanel(this);

		add(textPanel, BorderLayout.NORTH);
		add(tsp, BorderLayout.SOUTH);

	}
}
