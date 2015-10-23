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

public class TwitterBannierePanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4855570765891966882L;

	public static final int MIN_WIDTH_HEIGHT = 230;
	private TwitterFrame tf;

	private JLabel lbBanniere;

	public TwitterBannierePanel(TwitterFrame tf) {
		this.tf = tf;
		setPreferredSize(new Dimension(TwitterFrame.FRAME_WIDTH,
				MIN_WIDTH_HEIGHT));
		initBanner();
		initdata();
	}

	private void initBanner() {

		lbBanniere = new JLabel();
		Image img = TwitterApplication.getInstance().getMyBanniere().getImage();
		ImageIcon icon = new ImageIcon(scaledImage(img,
				TwitterFrame.FRAME_WIDTH, MIN_WIDTH_HEIGHT));
		lbBanniere.setIcon(icon);
	}

	/**
	 * Initialise les data
	 */
	public void initdata() {

		addMouseListener(this);
		setLayout(new GridBagLayout());
		add(lbBanniere, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 15, 0), 0, 0));	
		revalidate();
	}

	private Image scaledImage(Image img, int w, int h) {
		BufferedImage resizedImage = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedImage;
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