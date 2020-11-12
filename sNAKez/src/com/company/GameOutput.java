package com.company;

import javax.swing.*;
import java.awt.*;

public class GameOutput extends JPanel {

    private void drawGameState(int[][] cells, Graphics graphics){
        var graphics2d = (Graphics2D) graphics;
        int x = 0;
        int y = 0;
        for (int[] current : cells) {
            for (int cell : current){
                if(cell != 0){
                    if(cell == 1){
                        graphics2d.setColor(Color.red);
                    }else if(cell == 2){
                        graphics2d.setColor(Color.blue);
                    }else if(cell == 3){
                        graphics2d.setColor(Color.green);
                    }else if(cell == 4) {
                        graphics2d.setColor(Color.yellow);
                    }else if(cell == 5){
                        graphics2d.setColor(Color.magenta);
                    }else if(cell == 6){
                        graphics2d.setColor(Color.pink);
                    }
                }
                graphics2d.drawLine(x,y,x,y);
                y++;
            }
            x++;
        }
    }

    public void paintComponent(Graphics graphics, int[][] cells) {
        super.paintComponent(graphics);
        drawGameState(cells, graphics);
    }


}

