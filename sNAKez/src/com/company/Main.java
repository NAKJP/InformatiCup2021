package com.company;

import com.google.gson.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class Main extends JPanel {

    static Connector connector = new Connector();

    public static void main(String[] args) {
        connector.connect();
    }

}
