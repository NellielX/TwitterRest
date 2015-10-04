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
	// public static void main(String[] args) {
	//
	// try {
	//
	// // create HTTP Client
	// HttpClient httpClient = HttpClientBuilder.create().build();
	//
	// // Create new getRequest with below mentioned URL
	// HttpGet getRequest = new
	// HttpGet("http://localhost:8080/TwitterRest/ServiceTwitter/twitterservice/");
	//
	// // Add additional header to getRequest which accepts application/xml data
	// getRequest.addHeader("accept", "application/xml");
	//
	// // Execute your request and catch response
	// HttpResponse response = httpClient.execute(getRequest);
	//
	// // Check for HTTP response code: 200 = success
	// if (response.getStatusLine().getStatusCode() != 200) {
	// throw new RuntimeException("Failed : HTTP error code : " +
	// response.getStatusLine().getStatusCode());
	// }
	//
	// // Get-Capture Complete application/xml body response
	// BufferedReader br = new BufferedReader(new
	// InputStreamReader((response.getEntity().getContent())));
	// String output;
	// System.out.println("============Output:============");
	//
	// // Simply iterate through XML response and show on console.
	// while ((output = br.readLine()) != null) {
	// System.out.println(output);
	// }
	//
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
