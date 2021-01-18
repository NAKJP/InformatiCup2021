package com.company;

import java.net.URI;
import java.net.URISyntaxException;

public class Connector {

    private final String apiKey = System.getenv("KEY");
    private final String url = System.getenv("URL");
    private final String time_url = System.getenv("TIME_URL");

    public void connect(){
        try {
            // öffne den Websocket
            if (url.contains("?key=")){
                final Websocket clientEndPoint = new Websocket(new URI(url + apiKey));
            } else{
                final Websocket clientEndPoint = new Websocket(new URI(url + "?key=" + apiKey));
            }


            //warte auf Antwort 5 Minuten + Pingsicherheit
            Thread.sleep(301000);

        }catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        }catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }catch (RuntimeException ex) {
            System.err.println("Your Player is already in a game. - " + ex.getMessage());
        }
    }
}
