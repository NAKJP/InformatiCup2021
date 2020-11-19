package com.company;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Log {
    final String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format( new java.util.Date());

    private java.util.logging.Logger gameLogger = java.util.logging.Logger.getLogger("GameLog");
    private java.util.logging.Logger conectionLogger = java.util.logging.Logger.getLogger("ConnectionLog");
    FileHandler gameFileHandler;
    FileHandler connectionFileHandler;

    public Log(){
        try{
            gameFileHandler = new FileHandler("D:\\Programming\\Git\\InformatiCup2020\\sNAKez\\logging\\game.log");
            gameLogger.addHandler(gameFileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            gameFileHandler.setFormatter(formatter);

            gameLogger.info(timeStamp);
        }catch (SecurityException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            connectionFileHandler = new FileHandler("D:\\Programming\\Git\\InformatiCup2020\\sNAKez\\logging\\connection"+ timeStamp +".log");
            conectionLogger.addHandler(connectionFileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            connectionFileHandler.setFormatter(formatter);

            conectionLogger.info(timeStamp);
        }catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLog(String message){
        gameLogger.info(message);
    }

    public void connectionLog(String message){
        conectionLogger.info(message);
    }

}
