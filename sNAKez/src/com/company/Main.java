package com.company;

import javax.swing.*;

public class Main extends JPanel {

    static Connector connector = new Connector();

    public static void main(String[] args) {
        connector.connect();
    }

}
