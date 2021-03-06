package com.twitter.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.twitter.services.TwitterApplication;

public class TwitterUserDetailsPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4855570765891966882L;

	public static final int MIN_WIDTH_HEIGHT = 60;
	private TwitterFrame tf;

	private JLabel lbUser;
	private JLabel lbNbTweets;
	private JLabel lbNbAbonnements;
	private JLabel lbPseudo;
	private JLabel lbNbFriends;
	
	public TwitterUserDetailsPanel(TwitterFrame tf) {

		this.tf = tf;
		setPreferredSize(new Dimension(TwitterFrame.FRAME_WIDTH,
				MIN_WIDTH_HEIGHT));
		
		addMouseListener(this);
		setLayout(new GridBagLayout());
		initdata();
	}

	/**
	 * Initialise les data
	 */
	public void initdata() {
		removeAll();
		lbUser = new JLabel(TwitterApplication.getInstance().getMyImage());
		lbUser.setMinimumSize(new Dimension(MIN_WIDTH_HEIGHT, MIN_WIDTH_HEIGHT));

		lbNbTweets = new JLabel("Tweets : "
				+ TwitterApplication.getInstance().getNbTweet());

		lbNbAbonnements = new JLabel("Abonnements : "
				+ TwitterApplication.getInstance().getNbAbonnement());

		lbPseudo = new JLabel(TwitterApplication.getInstance().getMyPseudo());

		lbNbFriends = new JLabel("Nombre d'amis : "
				+ TwitterApplication.getInstance().getNbFriends());
		
		setLayout(new GridBagLayout());
		add(lbUser, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 100, 15, 0), 0, 0));	
		
		add(lbPseudo, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 200, 5, 0), 0, 0));	
		
		add(lbNbTweets, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 300, 5, 0), 0, 0));	
		
		add(lbNbAbonnements, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 400, 5, 0), 0, 0));	
		
		add(lbNbFriends, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 550, 5, 0), 0, 0));	

		revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			tf.getTwitterListPanel().updateJlist(null);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}