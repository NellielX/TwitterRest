package com.twitter.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.twitter.services.TwitterApplication;

public class TwitterHeaderPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4855570765891966882L;
	private TwitterFrame tf;

	private JLabel lbUser = new JLabel("User Picture");
	private JLabel lbNbTweets;
	private JLabel lbNbAbonnements;
	private JLabel lbPseudo;
	private JLabel lbNbFriends;
	private Image img;

	public TwitterHeaderPanel(TwitterFrame tf) {
		this.tf = tf;
		img = new ImageIcon(TwitterApplication.getInstance().getMyBanniere()).getImage();
		lbUser = new JLabel(TwitterApplication.getInstance().getMyImage());
		lbUser.addMouseListener(this);

		setLayout(new GridBagLayout());
		lbUser.setMinimumSize(new Dimension(300, 300));

		lbNbTweets = new JLabel("Tweets : "
				+ TwitterApplication.getInstance().getNbTweet());
		lbNbTweets.setMinimumSize(new Dimension(300, 300));

		lbNbAbonnements = new JLabel("Abonnements : "
				+ TwitterApplication.getInstance().getNbAbonnement());
		lbNbAbonnements.setMinimumSize(new Dimension(300, 300));

		lbPseudo = new JLabel("kingoftweets");
		lbPseudo.setMinimumSize(new Dimension(300, 300));

		lbNbFriends = new JLabel("Nombre d'amis : "
				+ TwitterApplication.getInstance().getNbFriends());
		lbNbFriends.setMinimumSize(new Dimension(300, 300));
		add(lbUser, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						10, 10, 15, 0), 0, 0));
		add(lbNbTweets, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						10, 100, 0, 0), 0, 0));
		add(lbNbAbonnements, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						10, 250, 0, 0), 0, 0));
		add(lbPseudo, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						10, 400, 0, 0), 0, 0));
	}

	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Image scaled = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	    g.drawImage(scaled, 0, 0, this);
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