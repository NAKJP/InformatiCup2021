package com.company;

public class Me {
    private int x;
    private int y;
    private String direction;
    private int speed;
    private boolean active;

    public Me(ServerMessage.Players.Player me) {
        this.x = me.getX();
        this.y = me.getY();
        this.direction = me.getDirection();
        this.speed = me.getSpeed();
        this.active = me.getActive();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean getActive() {
        return active;
    }
}
