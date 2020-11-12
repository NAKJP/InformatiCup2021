package com.company;

import java.net.URI;
import java.net.URISyntaxException;

// Lustiger Kommentar
public class Main {

    public static void main(String[] args) {
        establishConnection();
    }

    public static void establishConnection(){
        String apiKey = "4TB4RVHI6UZ4NQRIV4IDZYUERICKBWQMRMLSD5NVY756YYM5S3ZMJN2P";
        try {
            // open websocket
            final Websocket clientEndPoint = new Websocket(new URI("wss://msoll.de/spe_ed?key="+apiKey));

            // add listener
            clientEndPoint.addMessageHandler(new Websocket.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            clientEndPoint.sendMessage("{'action': 'change_nothing'}");

            Thread.sleep(300000);

        }catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        }catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }

}
