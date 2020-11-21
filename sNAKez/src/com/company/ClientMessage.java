package com.company;

import java.util.ArrayList;
import java.util.Random;

public class ClientMessage {
    private Random randomAction;
    private ArrayList<String> responses = new ArrayList<>();
    private String action;

    public ClientMessage(){
        fillResponses();
    }

    public void fillResponses(){
        responses.add("change_nothing");
        responses.add("turn_left");
        responses.add("turn_right");
        //responses.add("slow_down");
        //responses.add("speed_up");
    }

    public String randomResponse(){
        randomAction = new Random();
        int index = randomAction.nextInt(responses.size());
        return responses.get(index);
    }

    public String getAction(){
        return this.action;
    }
}
