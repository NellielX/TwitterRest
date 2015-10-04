package com.twitter.client;

import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.twitter.model.TwitterTimeLine;

public class TimelineCellRenderer extends JLabel implements
		ListCellRenderer<TwitterTimeLine> {

	private static final long serialVersionUID = -1536474351846893496L;
	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

	public TimelineCellRenderer() {
		setOpaque(true);
		setIconTextGap(12);
	}

	@Override
	public Component getListCellRendererComponent(
			JList<? extends TwitterTimeLine> list, TwitterTimeLine value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.getStatus());
		URL url = null;
		try {
			url = new URL(value.getProfileImage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(url, value.getPseudo());

		setIcon(icon);
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}
}
