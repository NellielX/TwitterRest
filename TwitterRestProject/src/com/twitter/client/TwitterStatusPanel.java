package com.twitter.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.twitter.services.TwitterApplication;

public class TwitterStatusPanel extends JPanel implements ActionListener {

	private static final String PUBLISH_TWEET = "publish tweet";
	private static final long serialVersionUID = 4855570765891966882L;
	private TwitterFrame tf;

	private JLabel lbUser;
	private JTextField txtStatus = new JTextField("", 15);
	private JButton btnUpdate = new JButton("Update");

	/**
	 * Pannel d'affichage du statut
	 * @param tf
	 */
	public TwitterStatusPanel(TwitterFrame tf) {
		this.tf = tf;
		lbUser = new JLabel(TwitterApplication.getInstance().getMyName());
		txtStatus.setActionCommand(PUBLISH_TWEET);
		btnUpdate.addActionListener(this);
		txtStatus.addActionListener(this);

		setLayout(new GridBagLayout());
		lbUser.setMinimumSize(new Dimension(300, 300));
		txtStatus.setMinimumSize(new Dimension(600, 300));
		btnUpdate.setMinimumSize(new Dimension(200, 300));

		add(lbUser, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(12,
						20, 15, 0), 0, 0));
		add(txtStatus, new GridBagConstraints(2, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(12, 20, 15, 10), 0, 0));
		add(btnUpdate, new GridBagConstraints(3, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(12,
						20, 15, 10), 0, 0));

	}

	@Override
	/**
	 * Action pour publier un tweet et refresh la liste
	 */
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (e.getSource().equals(btnUpdate)
				|| actionCommand.equals(PUBLISH_TWEET)) {
			String text = txtStatus.getText().trim();
			if (text.length() != 0) {
				TwitterApplication.getInstance().publish(text);
				tf.getTwitterListPanel().updateJlist(null);
				txtStatus.setText("");
				tf.getTwitterUserDetailsPanel().initdata();
			}
		}
	}
}
