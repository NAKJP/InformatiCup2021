package com.company;

public class ClientMessage {
    private String action;

    public ClientMessage(){
        this.action = "change_nothing";
    }

    public void setAction(String action){
        this.action = action;
    }

    public String getAction(){
        return this.action;
    }
}
