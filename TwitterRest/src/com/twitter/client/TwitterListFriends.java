package com.twitter.client;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twitter4j.TwitterException;
import twitter4j.User;

import com.twitter.model.CellData;
import com.twitter.services.TwitterApplication;

public class TwitterListFriends extends JPanel implements MouseListener {

	private static final long serialVersionUID = 6687548345793592620L;
	private TwitterFrame tf;
	private JList<CellData> listUser;
	private JScrollPane pane;

	/**
	 * Liste des amis
	 * 
	 * @param twitterFrame
	 */
	public TwitterListFriends(TwitterFrame twitterFrame) {
		this.tf = twitterFrame;
		setPreferredSize(new Dimension(250, 500));
		updateJlist();
	}

	/**
	 * Update de la Jlist
	 */
	public void updateJlist() {
		if (pane != null) {
			remove(pane);
		}
		DefaultListModel<CellData> listModel = new DefaultListModel<>();

		// -- -- -- -- -- Default Values -- -- --
		List<User> test = new ArrayList<>();
		try {
			test = TwitterApplication.getInstance().getListFriends();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		for (User user : test) {
			CellData element = new CellData();
			element.setProfileImage(user.getProfileImageURL());
			element.setPseudo(user.getScreenName());
			element.setStatus(user.getScreenName());
			listModel.addElement(element);
		}
		// -- -- -- -- -- -- -- -- -- -- -- -- --

		listUser = new JList<CellData>(listModel);
		listUser.setCellRenderer(new TimelineCellRenderer());
		listUser.addMouseListener(this);
		listUser.setVisibleRowCount(9);
		listUser.setPreferredSize(new Dimension(200, 500));
		pane = new JScrollPane(listUser);

		add(pane);
		revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			int index = listUser.locationToIndex(e.getPoint());
			if (index >= 0) {
				CellData aUser = listUser.getModel().getElementAt(index);
				String friend = aUser.getPseudo();
				System.out.println(friend);
				tf.getTwitterListPanel().updateJlist(friend);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
