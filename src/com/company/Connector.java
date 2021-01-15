package com.company;

import java.net.URI;
import java.net.URISyntaxException;

public class Connector {

    private final String url = "wss://msoll.de/spe_ed?key=";
    private final String apiKey = "4TB4RVHI6UZ4NQRIV4IDZYUERICKBWQMRMLSD5NVY756YYM5S3ZMJN2P";

    public void connect(){
        try {
            // öffne den Websocket
            final Websocket clientEndPoint = new Websocket(new URI(url + apiKey));

            //warte auf Antwort 5 Minuten + Pingsicherheit
            Thread.sleep(301000);

        }catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        }catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
