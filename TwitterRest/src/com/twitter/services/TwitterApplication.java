package com.twitter.services;

import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public abstract class TwitterApplication {

	private static final Logger logger = Logger
			.getLogger(TwitterApplication.class.getName());

	/**
	 * Méthode de connexion au compte Twitter
	 * 
	 * @return twitter Objet Twitter.
	 * @throws TwitterException
	 *             Gestion d'exeption par Twitter.
	 */
	private static Twitter login() throws TwitterException {

		Twitter twitter = new TwitterFactory().getInstance();
		try {
			RequestToken requestToken = twitter.getOAuthRequestToken();
			AccessToken accessToken = null;
			while (null == accessToken) {
				logger.fine("Open the following URL and grant access to your account:");
				logger.fine(requestToken.getAuthorizationURL());
				try {
					accessToken = twitter.getOAuthAccessToken(requestToken);
				} catch (TwitterException te) {
					if (401 == te.getStatusCode()) {
						logger.severe("Unable to get the access token.");
					} else {
						te.printStackTrace();
					}
				}
			}
			logger.info("Got access token.");
			logger.info("Access token: " + accessToken.getToken());
			logger.info("Access token secret: " + accessToken.getTokenSecret());
		} catch (IllegalStateException ie) {
			if (!twitter.getAuthorization().isEnabled()) {
				logger.severe("OAuth consumer key/secret is not set.");
			}
		}
		return twitter;
	}

	/**
	 * Méthode de publication des messages sur Twitter.
	 * 
	 * @param message
	 *            message a envoyer.
	 */
	public static void publish(String message) {

		try {
			Twitter twitter = login();
			Status status = twitter.updateStatus(message);
			logger.info("Successfully updated the status to ["
					+ status.getText() + "].");
		} catch (TwitterException te) {
			te.printStackTrace();
			logger.severe("Failed to get timeline: " + te.getMessage());
		}
	}

	/**
	 * Récupère la timeline de l'utilisateur twitter.
	 * 
	 * @return statuses , la liste des status du compte.
	 * @throws TwitterException
	 *             gestion de l'exeption par twitter
	 */
	public static List<Status> getUserTimeline() throws TwitterException {
		Twitter twitter = login();
		List<Status> statuses = twitter.getHomeTimeline();
		return statuses;
	}

	/**
	 * Retourne le nom de l'utilisateur
	 * 
	 * @return User.
	 */
	public static String getMyName() {
		try {
			Twitter twitter = login();
			return twitter.getScreenName();
		} catch (TwitterException e) {
			e.printStackTrace();
			return "User";
		}
	}

	/**
	 * Retourne l'image de l'utilisateur
	 * 
	 * @return Image.
	 */
	public static ImageIcon getMyImage() {
		try {
			Twitter twitter = login();
			User user = twitter.showUser(twitter.getId());
			String url = user.getProfileImageURL();
			ImageIcon img = new ImageIcon(url);
			return img;
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retourne la liste des amis
	 * 
	 * @return friendList.
	 * @throws TwitterException
	 */
	public static List<User> getListFriends() throws TwitterException {
		Twitter twitter = login();
		List<User> friendList = twitter.getFollowersList(twitter.getId(), -1);
		System.out.println("Number of Followers : " + friendList.size());
		return friendList;
	}

}
