package com.twitter.client;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import twitter4j.Status;
import twitter4j.TwitterException;

import com.twitter.model.CellData;
import com.twitter.services.TwitterApplication;

public class TwitterListPanel extends JPanel {

	private static final long serialVersionUID = 4558427651243865198L;
	private TwitterFrame tf;
	private JList<CellData> listTimeline;
	private JScrollPane pane;
	private String nameOfshownStatus = null;
	private Thread t;

	/**
	 * Liste des tweets
	 * 
	 * @param tf
	 */
	public TwitterListPanel(TwitterFrame tf) {
		this.tf = tf;
		setPreferredSize(new Dimension(800, 550));
		nameOfshownStatus = null;
		updateJlist(null);
		launchRefreshThread();
	}

	/**
	 * Refresh les Thread
	 */
	private void launchRefreshThread() {
		Runnable r = new Runnable() {
			public void run() {
				Thread one = new Thread() {
					public void run() {
						while (true) {
							try {
								Thread.sleep(60000);
								System.out
										.println("Mise à jour automatique de la timelme");
								updateJlist(nameOfshownStatus);
							} catch (InterruptedException v) {
								System.out.println(v);
							}
						}
					}
				};

				one.start();
			}
		};
		SwingUtilities.invokeLater(r);
	}

	/**
	 * Upadte de la liste des tweets
	 * 
	 * @param userName
	 */
	public void updateJlist(String userName) {
		nameOfshownStatus = userName;
		if (pane != null) {
			remove(pane);
			revalidate();
		}
		DefaultListModel<CellData> listModel = new DefaultListModel<>();

		// -- -- -- -- -- Default Values -- -- --
		List<Status> test = null;
		try {
			if (userName == null) {
				test = TwitterApplication.getInstance().getUserTimeline();
			} else {
				test = TwitterApplication.getInstance().getFriendTimeline(
						userName);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		for (Status status : test) {
			CellData element = new CellData();
			element.setProfileImage(status.getUser().getProfileImageURL());
			element.setPseudo(status.getUser().getName());
			element.setStatus(status.getText());
			listModel.addElement(element);
		}
		// -- -- -- -- -- -- -- -- -- -- -- -- --

		listTimeline = new JList<CellData>(listModel);
		listTimeline.setCellRenderer(new TimelineCellRenderer());
		listTimeline.setVisibleRowCount(9);
		pane = new JScrollPane(listTimeline);

		add(pane);
		revalidate();

		tf.repaint();
	}

}
