package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    private boolean firstAnswer = true;

    public static void main(String[] args) {
        Main game = new Main();
        game.establishConnection();
    }

    public void establishConnection(){
        String apiKey = "4TB4RVHI6UZ4NQRIV4IDZYUERICKBWQMRMLSD5NVY756YYM5S3ZMJN2P";
        try {
            // open websocket
            final Websocket clientEndPoint = new Websocket(new URI("wss://msoll.de/spe_ed?key="+apiKey));

            // add listener
            clientEndPoint.addMessageHandler(new Websocket.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                    JsonObject jobj = new Gson().fromJson(message, JsonObject.class);
                    if(firstAnswer) {
                        EventQueue.invokeLater(() -> {
                            int width = (int) jobj.get("width").getAsDouble();
                            int height = (int) jobj.get("height").getAsDouble();
                            var output = new OutputWindow(width, height);
                            output.setVisible(true);
                        });
                        firstAnswer = false;
                    }
                }
            });

            clientEndPoint.sendMessage("{'action': 'change_nothing'}");

            Thread.sleep(301000);

        }catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        }catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }

    public double getWidth(String inputString){
        JsonObject jsonObject = new JsonObject().getAsJsonObject(inputString);
        double width = jsonObject.get("width").getAsDouble();
        return width;
    }

    public double getHeight(String inputString){
        JsonObject jsonObject = new JsonObject().getAsJsonObject(inputString);
        double height = jsonObject.get("height").getAsDouble();
        return height;
    }

}
