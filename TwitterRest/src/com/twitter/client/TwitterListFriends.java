package com.twitter.client;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twitter4j.TwitterException;
import twitter4j.User;

import com.twitter.model.CellData;
import com.twitter.services.TwitterApplication;

public class TwitterListFriends extends JPanel {

	private static final long serialVersionUID = 6687548345793592620L;
	private TwitterFrame tf;
	private JList<CellData> listUser;
	private JScrollPane pane;

	public TwitterListFriends(TwitterFrame twitterFrame) {
		this.tf = twitterFrame;
		//setBackground(Color.yellow);
		setPreferredSize(new Dimension(250, 500));
		updateJlist();
	}

	public void updateJlist() {
		if (pane != null) {
			remove(pane);
			revalidate();
		}
		DefaultListModel<CellData> listModel = new DefaultListModel<>();

		// -- -- -- -- -- Default Values -- -- --
		List<User> test = null;
		try {
			test = TwitterApplication.getListFriends();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		for (User user : test) {
			CellData element = new CellData();
			element.setProfileImage(user.getProfileImageURL());
			element.setPseudo(user.getName());
			element.setStatus(user.getName());
			listModel.addElement(element);
		}
		// -- -- -- -- -- -- -- -- -- -- -- -- --

		listUser = new JList<CellData>(listModel);
		listUser.setCellRenderer(new TimelineCellRenderer());
		listUser.setVisibleRowCount(9);
		listUser.setPreferredSize(new Dimension(200, 500));
		pane = new JScrollPane(listUser);

		add(pane);
	}

}
