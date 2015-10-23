package com.twitter.client;

import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.twitter.model.CellData;

public class TimelineCellRenderer extends JLabel implements
		ListCellRenderer<CellData> {

	private static final long serialVersionUID = -1536474351846893496L;
	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

	public TimelineCellRenderer() {
		setOpaque(true);
		setIconTextGap(12);
	}

	@Override
	public Component getListCellRendererComponent(
			JList<? extends CellData> list, CellData value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String[] parts = value.getStatus().split("\\s+");
		StringBuilder finalString = new StringBuilder();
		for (String item : parts) {
			try {
				URL url = new URL(item);
				finalString.append("<a href=\"" + url + "\">" + url + "</a> ");
			} catch (MalformedURLException e) {
				finalString.append(item + "  ");
			}
		}
		String labelText = String.format("<html><div WIDTH=%d>%s</div><html>",
				500, finalString);
		setText(labelText);
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
