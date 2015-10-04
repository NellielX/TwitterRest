package com.twitter.client;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TwitterStatusPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4855570765891966882L;
	private TwitterFrame tf;

	private JLabel lbUser = new JLabel("User");
	private JTextField txtStatus = new JTextField("Enter new Status", 30);
	private JButton btnUpdate = new JButton("Update");

	public TwitterStatusPanel(TwitterFrame tf) {
		this.tf = tf;

		btnUpdate.addActionListener(this);
		txtStatus.addActionListener(this);

		setLayout(new GridBagLayout());
		lbUser.setMinimumSize(new Dimension(300, 300));
		txtStatus.setMinimumSize(new Dimension(600, 300));
		btnUpdate.setMinimumSize(new Dimension(200, 300));
		btnUpdate.setMaximumSize(new Dimension(200, 300));

		add(lbUser);
		add(txtStatus);
		add(btnUpdate);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
