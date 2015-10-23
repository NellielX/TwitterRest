package com.twitter.client;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.twitter.services.TwitterApplication;

public class TwitterHeaderPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4855570765891966882L;

	public static final int MIN_WIDTH_HEIGHT = 70;
	private TwitterFrame tf;

	private JLabel lbUser = new JLabel("User Picture");
	private JLabel lbNbTweets;
	private JLabel lbNbAbonnements;
	private JLabel lbPseudo;
	private JLabel lbNbFriends;

	public TwitterHeaderPanel(TwitterFrame tf) {
		
		this.tf = tf;
		setPreferredSize(new Dimension(TwitterFrame.FRAME_WIDTH, MIN_WIDTH_HEIGHT));
		initdata();
	}

	/**
	 * Initialise les data
	 */
	public void initdata() {
		removeAll();
		lbUser = new JLabel(TwitterApplication.getInstance().getMyImage());
		addMouseListener(this);
		setLayout(new GridBagLayout());
		lbUser.setMinimumSize(new Dimension(MIN_WIDTH_HEIGHT, MIN_WIDTH_HEIGHT));

		lbNbTweets = new JLabel("Tweets : "
				+ TwitterApplication.getInstance().getNbTweet());

		lbNbAbonnements = new JLabel("Abonnements : "
				+ TwitterApplication.getInstance().getNbAbonnement());

		lbPseudo = new JLabel(TwitterApplication.getInstance().getMyPseudo());

		lbNbFriends = new JLabel("Nombre d'amis : "
				+ TwitterApplication.getInstance().getNbFriends());

		add(lbUser);
		add(lbNbTweets);
		add(lbNbAbonnements);
		add(lbPseudo);
		add(lbNbFriends);
		
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