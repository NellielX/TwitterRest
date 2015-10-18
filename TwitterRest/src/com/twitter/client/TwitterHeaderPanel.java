package com.twitter.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.twitter.model.CellData;
import com.twitter.services.TwitterApplication;

public class TwitterHeaderPanel extends JPanel implements MouseListener{

	private static final long serialVersionUID = 4855570765891966882L;
	private TwitterFrame tf;

	private JLabel lbUser = new JLabel("User Picture");

	public TwitterHeaderPanel(TwitterFrame tf) {
		this.tf = tf;
		
		lbUser = new JLabel();
		lbUser.setIcon(TwitterApplication.getInstance().getMyImage());
		lbUser.addMouseListener(this);
		
		setLayout(new GridBagLayout());
		lbUser.setMinimumSize(new Dimension(300, 300));
		
		add(lbUser, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						10, 15, 0), 0, 0));
		
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