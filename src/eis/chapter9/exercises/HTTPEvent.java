package eis.chapter9.exercises;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Exercise 3 from chapter 9.
 * 
 * @author Marco Faella
 * @version 1.0
 */
public class HTTPEvent implements Event {
    private URL startURL, stopURL;
        
    public HTTPEvent(String startURL, String stopURL) throws MalformedURLException {
        this.startURL = new URL(startURL);
        this.stopURL = new URL(stopURL);
    }
    
    @Override 
    public void start() {
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) startURL.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode(); // Forces the request to be made
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override 
    public void stop() {
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) stopURL.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode(); // Forces the request to be made
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
