package com.twitter.services;

import java.util.List;
import java.util.logging.Logger;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterApplication {
	
    private final Logger logger = Logger.getLogger(TwitterApplication.class.getName());

    public static void main(String[] args) throws TwitterException {
        new TwitterApplication().publish("koko");
        
        List<Status> test = new TwitterApplication().getUserTimeline();
        
        for (Status status : test) {
        	System.out.println(status.getUser().getProfileImageURL());
            System.out.println(status.getUser().getName() + ":" +
                               status.getText());
        }
    }

    /**
     * Méthode de connexion au compte Twitter 
     * @return twitter Objet Twitter.
     * @throws TwitterException Gestion d'exeption par Twitter.
     */
    private Twitter login() throws TwitterException{
    	
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
     * @param message message a envoyer.
     */
    private void publish(String message){

       try {
    	   Twitter twitter = login();
           Status status = twitter.updateStatus(message);
           logger.info("Successfully updated the status to [" + status.getText() + "].");
       } catch (TwitterException te) {
           te.printStackTrace();
           logger.severe("Failed to get timeline: " + te.getMessage());
       } 
    }
   
    /**
     * Récupère la timeline de l'utilisateur twitter.
     * @return statuses , la liste des status du compte. 
     * @throws TwitterException gestion de l'exeption par twitter
     */
    public List<Status> getUserTimeline() throws TwitterException{
        Twitter twitter = login();
        List<Status> statuses = twitter.getHomeTimeline();
		return statuses; 	
    }
    
}