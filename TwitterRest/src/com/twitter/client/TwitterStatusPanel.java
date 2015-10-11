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

	private JLabel lbUser = new JLabel("User");
	private JTextField txtStatus = new JTextField("Enter new Status", 15);
	private JButton btnUpdate = new JButton("Update");

	public TwitterStatusPanel(TwitterFrame tf) {
		this.tf = tf;
		lbUser = new JLabel(TwitterApplication.getMyName());
		lbUser.setIcon(TwitterApplication.getMyImage());
		txtStatus.setActionCommand(PUBLISH_TWEET);
		btnUpdate.addActionListener(this);
		txtStatus.addActionListener(this);

		setLayout(new GridBagLayout());
		lbUser.setMinimumSize(new Dimension(300, 300));
		txtStatus.setMinimumSize(new Dimension(600, 300));
		btnUpdate.setMinimumSize(new Dimension(200, 300));

		add(lbUser, new GridBagConstraints(1, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						10, 15, 0), 0, 0));
		add(txtStatus, new GridBagConstraints(2, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 10, 15, 10), 0, 0));
		add(btnUpdate, new GridBagConstraints(3, 1, 1, 1, 2.0, 2.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 15, 10), 0, 0));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if (e.getSource().equals(btnUpdate)|| actionCommand.equals(PUBLISH_TWEET)) {
			String text = txtStatus.getText().trim();
			if (text.length() != 0) {
				TwitterApplication.publish(text);
				tf.getTwitterListPanel().updateJlist();
				txtStatus.setText("");
			}
		}
	}
}
