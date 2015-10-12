package com.twitter.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.twitter.services.TwitterApplication;

public class TwitterHeaderPanel extends JPanel{

	private static final long serialVersionUID = 4855570765891966882L;
	private TwitterFrame tf;

	private JLabel lbUser = new JLabel("User Picture");

	public TwitterHeaderPanel(TwitterFrame tf) {
		this.tf = tf;
		
		lbUser = new JLabel();
		lbUser.setIcon(TwitterApplication.getInstance().getMyImage());

		setLayout(new GridBagLayout());
		lbUser.setMinimumSize(new Dimension(300, 300));
		
		add(lbUser, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						10, 15, 0), 0, 0));
		
	}

}