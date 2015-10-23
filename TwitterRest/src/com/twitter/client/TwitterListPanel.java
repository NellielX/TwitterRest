package com.twitter.client;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import twitter4j.Status;
import twitter4j.TwitterException;

import com.twitter.model.CellData;
import com.twitter.services.TwitterApplication;

public class TwitterListPanel extends JPanel implements MouseListener{

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
		TimelineCellRenderer tlc =  new TimelineCellRenderer();
		listTimeline.setCellRenderer(tlc);
		listTimeline.addMouseListener(this);
		listTimeline.setVisibleRowCount(9);
		pane = new JScrollPane(listTimeline);

		add(pane);
		revalidate();

		tf.repaint();
	}


	public List<String> getUrl(String text) {
		List<String> listUrl = new ArrayList<String>();
		String[] parts = text.split("\\s+");
		for (String item : parts) {
			try {
				URL url = new URL(item);
				listUrl.add(url.toString());
			} catch (MalformedURLException e) {
				//l'item n'est pas une url
			}
		}
		return listUrl;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			CellData selectedCell = (CellData) listTimeline.getSelectedValue();
			String textContenantLiens = selectedCell.getStatus();
			List<String> listUrl = getUrl(textContenantLiens);
			String[] possibilities = listUrl
					.toArray(new String[listUrl.size()]);
			;
			String urlChoisi = (String) JOptionPane.showInputDialog(this,
					"Quels liens souhaitez vous visiter ?", "Accès aux liens",
					JOptionPane.PLAIN_MESSAGE, null, possibilities, "ham");
			if ((urlChoisi != null) && (urlChoisi.length() > 0)) {
				try {
					Desktop.getDesktop().browse(URI.create(urlChoisi));
				} catch (IOException e1) {

				}
				return;
			}
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
