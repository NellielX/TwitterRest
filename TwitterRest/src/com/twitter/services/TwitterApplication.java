package com.twitter.services;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterApplication {

	private static final Logger logger = Logger
			.getLogger(TwitterApplication.class.getName());

	/**
	 * Méthode de connexion au compte Twitter
	 * 
	 * @return twitter Objet Twitter.
	 * @throws TwitterException
	 *             Gestion d'exeption par Twitter.
	 */
	private static TwitterApplication TWITTER_APP_INSTANCE = null;
	private static Twitter twitter;

	private TwitterApplication() {

	}

	/**
	 * Renvoi une instance de twitter
	 * 
	 * @return TWITTER_APP_INSTANCE.
	 */
	public static TwitterApplication getInstance() {
		if (TWITTER_APP_INSTANCE == null) {
			TWITTER_APP_INSTANCE = new TwitterApplication();
			twitter = login();
		}
		return TWITTER_APP_INSTANCE;
	}

	/**
	 * Fonction de login sur twitter.
	 * 
	 * @return twitter Objet Twitter
	 */
	private static Twitter login() {
		logger.info("Création du login");
		twitter = new TwitterFactory().getInstance();
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
		} catch (IllegalStateException | TwitterException ie) {
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
	public void publish(String message) {

		try {
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
	 *             Gestion de l'exeption par twitter
	 */
	public List<Status> getUserTimeline() throws TwitterException {
		logger.info("Récupération de le timeline d'un utilisateur");
		List<Status> statuses = twitter.getHomeTimeline();
		return statuses;
	}

	/**
	 * Récupère la timeline d'un ami de l'utilisateur twitter.
	 * 
	 * @return statuses , la liste des status du compte.
	 * @throws TwitterException
	 *             Gestion de l'exeption par twitter
	 */
	public List<Status> getFriendTimeline(String friendName)
			throws TwitterException {
		logger.info("Récupération de la timeline d'un ami");
		String searchuser[] = { friendName };
		ResponseList<User> users_list = twitter.lookupUsers(searchuser);
		List<Status> statuses = new ArrayList<>();
		for (User user : users_list) {
			if (user.getStatus() != null) {
				statuses = twitter.getUserTimeline(user.getId());
			}
		}
		return statuses;
	}

	/**
	 * Retourne le nom de l'utilisateur
	 * 
	 * @return User.
	 */
	public String getMyName() {
		logger.info("Récupération du nom de tweet de l'utilisateur");
		String userName = "User";
		try {
			userName = twitter.getScreenName();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return userName;
	}

	/**
	 * Retourne l'image de l'utilisateur
	 * 
	 * @return Image.
	 */
	public ImageIcon getMyImage() {
		logger.info("Récupération de l'image du profil");
		ImageIcon img = null;
		try {
			User user = twitter.showUser(twitter.getId());
			URL url = new URL(user.getProfileImageURL());
			img = new ImageIcon(url);
		} catch (TwitterException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return img;

	}

	/**
	 * Retourne le background de l'utilisateur.
	 * 
	 * @return img.
	 */
	public ImageIcon getMyBanniere() {
		logger.info("Récupération de la bannière.");
		String defaultUrlImage = "http://www.blirk.net/wallpapers/1920x1200/pink-sky-wallpaper-1.jpg";
		try {
			User user = twitter.showUser(twitter.getId());
			String twitterUrl = user.getProfileBannerURL();
			URL url;
			if (twitterUrl == null)
				url = new URL(defaultUrlImage);
			else
				url = new URL(twitterUrl);
			ImageIcon img = new ImageIcon(url);
			return img;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retourne la couleur de fond du profile utilisateur
	 * 
	 * @return couleurFond.
	 * @throws MalformedURLException
	 * @throws TwitterException
	 * @throws IllegalStateException
	 */
	public Color getMyBackgroundColor() {
		logger.info("Récupération de la couleur de fond de tweeter");
		try {
			User user = twitter.showUser(twitter.getId());
			String couleur = user.getProfileBackgroundColor();
			if (couleur.equals("000000"))
				return Color.decode("#c0deed");
			Color couleurFond = Color.decode("#" + couleur + "");
			return couleurFond;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return Color.decode("#c0deed");
		} catch (TwitterException e) {
			e.printStackTrace();
			return Color.decode("#c0deed");
		}
	}

	/**
	 * Retourne la liste des amis
	 * 
	 * @return friendList.
	 * @throws TwitterException
	 */
	public List<User> getListFriends() throws TwitterException {
		logger.info("Récupération des amis");
		List<User> friendList = twitter.getFollowersList(twitter.getId(), -1);
		return friendList;
	}

	/**
	 * Retourne le nombre d'amis
	 * 
	 * @return nbFriends.
	 * @throws IllegalStateException
	 * @throws TwitterException
	 */
	public int getNbFriends() {
		logger.info("Récupération du nombre d'amis");
		User user = null;
		try {
			user = twitter.showUser(twitter.getId());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		int nbFriends = user.getFollowersCount();
		return nbFriends;
	}

	/**
	 * Retourne le nombre de tweet
	 * 
	 * @return nbStatus.
	 * @throws IllegalStateException
	 * @throws TwitterException
	 */
	public int getNbTweet() {
		logger.info("Récupération du nombre des tweets");
		User user = null;
		try {
			user = twitter.showUser(twitter.getId());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		int nbStatus = user.getStatusesCount();
		return nbStatus;
	}

	/**
	 * Retourne le nombre d'abonnement
	 * 
	 * @return nbAbonnement.
	 * @throws IllegalStateException
	 * @throws TwitterException
	 */
	public int getNbAbonnement() {
		logger.info("Récupération du nombre d'abonnés");
		User user = null;
		try {
			user = twitter.showUser(twitter.getId());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		int nbAbonnement = user.getFriendsCount();
		return nbAbonnement;
	}

	/**
	 * Retourne le pseudo de l'utilisateur
	 * 
	 * @return pseudo.
	 */
	public String getMyPseudo() {
		logger.info("Récupération du pseudo");
		User user = null;
		try {
			user = twitter.showUser(twitter.getId());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		String pseudo = user.getName();
		return pseudo;
	}
}
