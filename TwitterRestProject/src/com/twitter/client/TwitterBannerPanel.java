package com.twitter.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.twitter.services.TwitterApplication;

public class TwitterBannerPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4855570765891966882L;

	public static final int MIN_WIDTH_HEIGHT = 200;
	private TwitterFrame tf;

	private JLabel lbBanniere;

	public TwitterBannerPanel(TwitterFrame tf) {
		this.tf = tf;
		setPreferredSize(new Dimension(TwitterFrame.FRAME_WIDTH,
				MIN_WIDTH_HEIGHT));
		initBanner();
		initdata();
	}

	private void initBanner() {

		lbBanniere = new JLabel();
		Image img = TwitterApplication.getInstance().getMyBanniere().getImage();
		ImageIcon icon = new ImageIcon(img);
		lbBanniere.setIcon(icon);
	}

	/**
	 * Initialise les data
	 */
	public void initdata() {

		addMouseListener(this);
		setLayout(new FlowLayout());
		add(lbBanniere);
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