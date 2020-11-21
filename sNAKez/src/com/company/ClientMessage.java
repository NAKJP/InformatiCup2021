package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientMessage {
    private Random randomAction;
    private ArrayList<String> responses = new ArrayList<>();
    private String action;
    private ServerMessage serverMessage;
    private List<String> possibleMoves;
    private Me myPlayer;

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

    public String randomResponse(int[][] cells, Me myPlayer){
        randomAction = new Random();
        getPossibleMoves(cells, myPlayer);
        int index = randomAction.nextInt(responses.size());
        return responses.get(index);
    }

    public List<String> getPossibleMoves(int[][] cells, Me myPlayer){
        int meX = myPlayer.getX();
        int meY = myPlayer.getY();
        String direction = myPlayer.getDirection();
        int speed = myPlayer.getSpeed();
        boolean left;
        boolean right;
        boolean head;

        switch(direction){
            case "down":
                head = cells[meX+1][meY]==0;
                left = cells[meX][meY+1]==0;
                right = cells[meX][meY-1]==0;
                break;
            case "up":
                head = cells[meX-1][meY]==0;
                left = cells[meX][meY-1]==0;
                right = cells[meX][meY+1]==0;
                break;
            case "left":
                head = cells[meX][meY-1]==0;
                left = cells[meX+1][meY]==0;
                right = cells[meX-1][meY]==0;
                break;
            case "right":
                head = cells[meX][meY+1]==0;
                left = cells[meX-1][meY]==0;
                right = cells[meX+1][meY]==0;
                break;
            default: head = false;
        }

        if(head == true){

        }


        possibleMoves.add("Test");
        return possibleMoves;
    }

    public String getAction(){
        return this.action;
    }
}
