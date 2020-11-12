package com.company;

import javax.swing.*;
import java.awt.*;

public class OutputWindow extends JFrame {

    public OutputWindow(int width, int height){
        initUI(width, height);
    }

    private void initUI(int width, int height){

        var gameOutput = new GameOutput();
        add(gameOutput);

        setPreferredSize(new Dimension(width, height));
        setTitle("sNAKez");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
