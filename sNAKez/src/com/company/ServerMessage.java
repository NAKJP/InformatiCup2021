package com.company;

public class ServerMessage {
    private int width;
    private int height;
    private int[][] cells;
    private Players players;
    private int you;
    private boolean running;
    private String deadline;

    class Players{
        // TODO Fix Mapping
        private Player player1;
        private Player player2;
        private Player player3;
        private Player player4;
        private Player player5;
        private Player player6;
        public Players(){}

        public void setPlayer1(Player player1){
            this.player1 = player1;
        }
        public void setPlayer2(Player player2){
            this.player2 = player2;
        }
        public void setPlayer3(Player player3){
            this.player3 = player3;
        }
        public void setPlayer4(Player player4){
            this.player4 = player4;
        }
        public void setPlayer5(Player player5){
            this.player5 = player5;
        }
        public void setPlayer6(Player player6){
            this.player6 = player6;
        }
        public Player getPlayer1(){
            return player1;
        }
        public Player getPlayer2(){
            return player2;
        }
        public Player getPlayer3(){
            return player3;
        }
        public Player getPlayer4(){
            return player4;
        }
        public Player getPlayer5(){
            return player5;
        }
        public Player getPlayer6(){
            return player6;
        }

        public class Player{
            private int x;
            private int y;
            private String direction;
            private int speed;
            private boolean active;

            public Player(){

            }
            public void setX(int x){
                this.x = x;
            }
            public void setY(int y){
                this.y = y;
            }
            public void setDirection(String direction){
                this.direction = direction;
            }
            public void setSpeed(int speed){
                this.speed = speed;
            }
            public void setActive(boolean active){
                this.active = active;
            }
            public int getX(){
                return x;
            }
            public int getY(){
                return y;
            }
            public String getDirection(){
                return direction;
            }
            public int getSpeed(){
                return speed;
            }
            public boolean getActive(){
                System.out.println(this.x + " " + this.direction);
                return active;
            }

        }
    }

    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setCells(int[][] cells){
        this.cells = cells;
    }
    public void setPlayers(Players players){
        this.players = players;
    }
    public void setInt(int you){
        this.you = you;
    }
    public void setRunning(boolean running){
        this.running = running;
    }
    public void setDeadline(String deadline){
        this.deadline = deadline;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int[][] getCells(){
        return cells;
    }
    public Players getPlayers(){
        return players;
    }
    public int getInt(){
        return you;
    }
    public boolean getRunning(){
        return running;
    }
    public String getDeadline(){
        return deadline;
    }
}
