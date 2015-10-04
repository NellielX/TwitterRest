package com.twitter.client;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

public class TwitterListPanel extends JPanel {

	private static final long serialVersionUID = 4558427651243865198L;
	private TwitterFrame tf;
	private JList<TwitterTimeLine> listTimeline;

	public TwitterListPanel(TwitterFrame tf) {
		this.tf = tf;
		initJList();
	}

	private void initJList() {
		DefaultListModel<TwitterTimeLine> listModel = new DefaultListModel<>();

		// -- -- -- -- -- Default Values -- -- --
		TwitterTimeLine element = new TwitterTimeLine();
		element.setProfileImage("url image");
		element.setPseudo("Toto");
		element.setStatus("Status");
		// -- -- -- -- -- -- -- -- -- -- -- -- --

		listModel.addElement(element);
		listTimeline = new JList<TwitterTimeLine>(listModel);
	}
}
