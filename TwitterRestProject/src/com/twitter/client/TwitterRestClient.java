package com.twitter.client;

import javax.swing.SwingUtilities;


public class TwitterRestClient {
	
	public static void main(String[] args) {

		Runnable r = new Runnable() {
			public void run() {
				new TwitterFrame();
			}
		};
		SwingUtilities.invokeLater(r);
	}
}
