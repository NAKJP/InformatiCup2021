package com.company;

import com.google.gson.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends JPanel {

    public static JsonObject jobj;

    public static void main(String[] args) {
        //erstelle Ausgabefenster
        JFrame snakez = new JFrame();

        snakez.setSize(1000,1000);
        snakez.setTitle("sNAKez - connecting...");
        snakez.setVisible(true);

        String url = "wss://msoll.de/spe_ed?key=";
        String apiKey = "4TB4RVHI6UZ4NQRIV4IDZYUERICKBWQMRMLSD5NVY756YYM5S3ZMJN2P";
        try {
            // öffne den Websocket
            final Websocket clientEndPoint = new Websocket(new URI(url + apiKey));

            // verarbeite Servernachrichten
            clientEndPoint.addMessageHandler(new Websocket.MessageHandler() {
                public void handleMessage(String message) {
                    snakez.setTitle("sNAKesz - connected");
                    clientEndPoint.sendMessage("{\"action\": \"change_nothing\"}");
                    System.out.println(message);

                    //erstelle fülle das JSonObject mit der Nachricht vom Server
                    jobj = new Gson().fromJson(message, JsonObject.class);

                    //Erstelle Panel auf dem gemalt wird
                    Main output = new Main();
                    output.setSize(output.getWidthFromJson(),output.getHeightFromJson());
                    //füge das Panel dem Ausgabefenster hinzu
                    snakez.add(output);
                    //erneuere das Bild auf dem Panel
                    output.revalidate();
                    output.repaint();
                }
            });

            //warte auf Antwort 5 Minuten + Pingsicherheit
            Thread.sleep(301000);

        }catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        }catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }


    }

    //Funktion zum Bemalen des Panels wird aus dem Konstruktor des JPanels aufgerufen
    public void paint(Graphics g){
        int width = getWidthFromJson() * 10;
        int height = getHeightFromJson() * 10;

        setSize(width + 5,height + 5);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));

        int[][] cells = getCellsFromString();

        for(int i = 0; i < getHeightFromJson() - 1; i++){
            for(int j = 0; j < getWidthFromJson() - 1; j++){
                switch(cells[i][j]){
                    case 1:
                        g2d.setColor(Color.red);
                        break;
                    case 2:
                        g2d.setColor(Color.blue);
                        break;
                    case 3:
                        g2d.setColor(Color.green);
                        break;
                    case 4:
                        g2d.setColor(Color.yellow);
                        break;
                    case 5:
                        g2d.setColor(Color.magenta);
                        break;
                    case 6:
                        g2d.setColor(Color.pink);
                        break;
                    default:
                        g2d.setColor(Color.white);
                        break;
                }
                g2d.draw(new Line2D.Float((i*10)+5,(j*10)+5,(i*10)+5,(j*10)+5));
            }
        }
    }

    //Funktion zum extrahieren der Breite des Spielfeldes aus der JSon
    private int getWidthFromJson(){
        return (int) jobj.get("width").getAsDouble();
    }

    //Funktion zum extrahieren der Höhe des Spielfeldes aus der JSon
    private int getHeightFromJson(){
        return (int) jobj.get("height").getAsDouble();
    }

    //Funktion zum extrahieren des Spielfeldes aus der JSon
    private int[][] getCellsFromString(){
        JsonArray jsonArray = jobj.get("cells").getAsJsonArray();
        int[][] cells = new int[getHeightFromJson()][getWidthFromJson()];
        for ( int i = 0; i < getHeightFromJson() - 1; i++) {
            for(int j = 0; j < getWidthFromJson() - 1; j++){
                JsonArray jsonArrayCollumn = jsonArray.get(i).getAsJsonArray();
                cells[i][j] = jsonArrayCollumn.get(j).getAsInt();
            }
        }
        return cells;
    }

}
