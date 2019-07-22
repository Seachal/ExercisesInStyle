package eis.chapter9.exercises;

import java.net.MalformedURLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *  Exercise 3 from chapter 9.
 *   
 *  @version 1.0
 *  @author Marco Faella
 */
public class Exercise3 {

    public static LocalTime afterSeconds(int seconds) {
        return LocalTime.now().plus(seconds, ChronoUnit.SECONDS);
    }
    
    public static void main(String[] args) throws MalformedURLException {
        Schedule<HTTPEvent> netSchedule = new Schedule<>();
        
        // webhook is a site that shows in real time the http requests it receives 
        String webHookUrl = "https://webhook.site/21f57f64-14cd-4ea7-98c8-56dcbac53969";
        
        netSchedule.addEvent(new HTTPEvent("http://www.google.com", "http://request.urih.com"), afterSeconds(5), afterSeconds(8));
        netSchedule.addEvent(new HTTPEvent(webHookUrl + "/start", webHookUrl + "/stop"), afterSeconds(12), afterSeconds(14));
        netSchedule.launch();
        
        while (netSchedule.isActive()) {
            System.out.println(netSchedule.currentEvent());
            try { Thread.sleep(100); }
            catch (InterruptedException e) { return; }
        }
    }
}