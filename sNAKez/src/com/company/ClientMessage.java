package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientMessage {
    private Random randomAction;
    private ArrayList<String> responses = new ArrayList<>();
    private String action;
    private ServerMessage serverMessage;
    private ArrayList<String> possibleMoves = new ArrayList<>();
    private Me myPlayer;

    public ClientMessage(){
    }

    public void fillResponses(){
        responses.add("change_nothing");
        responses.add("turn_left");
        responses.add("turn_right");
        //responses.add("slow_down");
        //responses.add("speed_up");
    }

    public String randomResponse(int[][] cells, Me myPlayer){
        long start = System.currentTimeMillis();
        randomAction = new Random();
        List<String> myPossibleMoves = getPossibleMoves(cells, myPlayer);
        int index = randomAction.nextInt(myPossibleMoves.size());
        long end =  System.currentTimeMillis();
        System.out.println(end - start);
        if(myPossibleMoves.size() == 0){
            return "change_nothing";
        }
        return myPossibleMoves.get(index);
    }

    public List<String> getPossibleMoves(int[][] cells, Me myPlayer){
        int meX = myPlayer.getX();
        int meY = myPlayer.getY();
        String direction = myPlayer.getDirection();
        int speed = myPlayer.getSpeed();
        boolean left = false;
        boolean right= false;
        boolean head = false;

        switch(direction){
            case "down":
                if(!(meX + 1 >= cells.length)) {
                    head = cells[meX + 1][meY] == 0 && cells[meX + 2][meY] == 0;
                }else{
                    head = false;
                }
                if(!(meY + 1 >= cells[0].length)) {
                    left = cells[meX][meY + 1] == 0 && cells[meX][meY + 2] == 0;
                }else{
                    left = false;
                }
                if(!(meY - 1 >= cells[0].length)) {
                    right = cells[meX][meY - 1] == 0 && cells[meX][meY - 2] == 0;
                }else{
                    right = false;
                }

                break;
            case "up":
                if(!(meX - 1 >= cells.length)) {
                    head = cells[meX - 1][meY] == 0 && cells[meX - 2][meY] == 0;
                }else{
                    head = false;
                }
                if(!(meY - 1 >= cells[0].length)) {
                    left = cells[meX][meY - 1] == 0 && cells[meX][meY - 2] == 0;
                }else{
                    left = false;
                }
                if(!(meY + 1 >= cells[0].length)) {
                    right = cells[meX][meY + 1] == 0 && cells[meX][meY + 2] == 0;
                }else{
                    right = false;
                }
                break;
            case "left":
                if(!(meY - 1 >= cells[0].length)) {
                    head = cells[meX][meY - 1] == 0 && cells[meX][meY - 2] == 0;
                }else{
                    head = false;
                }
                if(!(meX + 1 >= cells.length)) {
                    left = cells[meX + 1][meY] == 0 && cells[meX + 2][meY] == 0;
                }else{
                    left = false;
                }
                if(!(meX - 1 >= cells[0].length)) {
                    right = cells[meX - 1][meY] == 0 && cells[meX - 2][meY] == 0;
                }else{
                    right = false;
                }
                break;
            case "right":
                if(!(meY + 1 >= cells.length)) {
                    head = cells[meX][meY-1] == 0 && cells[meX][meY-2] == 0;
                }else{
                    head = false;
                }
                if(!(meX - 1 >= cells.length)) {
                    left = cells[meX + 1][meY] == 0 && cells[meX + 2][meY] == 0;
                }else{
                    left = false;
                }
                if(!(meX + 1 >= cells.length)) {
                    right = cells[meX - 1][meY] == 0 && cells[meX - 2][meY] == 0;
                }else{
                    right = false;
                }
                break;
        }
        possibleMoves.clear();

        if(head){
            possibleMoves.add("change_nothing");
        }
        if(right){
            possibleMoves.add("turn_right");
        }
        if(left){
            possibleMoves.add("turn_left");
        }
        return possibleMoves;
    }

    public String getAction(){
        return this.action;
    }
}
