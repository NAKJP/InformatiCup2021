package com.company;

import com.Application.Test.TestGraphicOutput;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class GraphicOutput extends JPanel {

    private JFrame snakez = new JFrame();
    private  JsonObject jobj;
    private boolean initialMessage;

    public void initializeOutput(){
        snakez.setSize(1000,1000);
        snakez.setTitle("sNAKez - connecting...");
        snakez.setVisible(true);
        initialMessage = true;
    }

    public void outputGame(String message){
        if(initialMessage){
            snakez.setTitle("sNAKez - connected");
            initialMessage = false;
        }
        jobj = new Gson().fromJson(message, JsonObject.class);
        snakez.add(this);
        setSize(getWidthFromJson(),getHeightFromJson());
        //revalidate();
        repaint();
    }

    public void paint(Graphics g){
        int width = getWidthFromJson() * 15;
        int height = getHeightFromJson() * 15;

        setSize(width + 5,height + 5);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));

        int[][] cells = getCellsFromString();

        for(int i = 0; i < getHeightFromJson(); i++){
            for(int j = 0; j < getWidthFromJson(); j++){
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
                g2d.draw(new Line2D.Float((j*10)+5,(i*10)+5,(j*10)+5,(i*10)+5));
            }
        }
    }

    private int getWidthFromJson(){
        return (int) jobj.get("width").getAsDouble();
    }

    private int getHeightFromJson(){
        return (int) jobj.get("height").getAsDouble();
    }

    private int[][] getCellsFromString(){
        JsonArray jsonArray = jobj.get("cells").getAsJsonArray();
        int[][] cells = new int[getHeightFromJson()][getWidthFromJson()];
        for ( int i = 0; i < getHeightFromJson(); i++) {
            for(int j = 0; j < getWidthFromJson(); j++){
                JsonArray jsonArrayCollumn = jsonArray.get(i).getAsJsonArray();
                cells[i][j] = jsonArrayCollumn.get(j).getAsInt();
            }
        }
        return cells;
    }

    public boolean isActive(String message){
        jobj = jobj = new Gson().fromJson(message, JsonObject.class);
        String me = jobj.get("you").getAsString();
        JsonObject players = jobj.get("players").getAsJsonObject();
        JsonObject myPlayer = players.get(me).getAsJsonObject();
        return myPlayer.get("active").getAsBoolean();
    }



}
