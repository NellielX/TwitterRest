package com.twitter.client;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import twitter4j.Status;
import twitter4j.TwitterException;

import com.twitter.model.TwitterTimeLine;
import com.twitter.services.TwitterApplication;

public class TwitterListPanel extends JPanel {

	private static final long serialVersionUID = 4558427651243865198L;
	private TwitterFrame tf;
	private JList<TwitterTimeLine> listTimeline;

	public TwitterListPanel(TwitterFrame tf) {
		this.tf = tf;
		updateJlist();
	}

	public void updateJlist() {
		DefaultListModel<TwitterTimeLine> listModel = new DefaultListModel<>();

		// -- -- -- -- -- Default Values -- -- --
		List<Status> test = null;
		try {
			test = new TwitterApplication().getUserTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		for (Status status : test) {
			TwitterTimeLine element = new TwitterTimeLine();
			element.setProfileImage(status.getUser().getProfileImageURL());
			element.setPseudo(status.getUser().getName());
			element.setStatus(status.getText());
			listModel.addElement(element);
		}
		System.out.println("Size of status list : " + listModel.size());
		// -- -- -- -- -- -- -- -- -- -- -- -- --

		listTimeline = new JList<TwitterTimeLine>(listModel);
		add(listTimeline);
	}

}
