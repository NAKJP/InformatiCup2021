package com.company;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class Websocket {

    Session userSession;
    GraphicOutput output;
    Log logger = new Log();
    private MessageHandler messageHandler;

    public Websocket (URI endpointURI){
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        String time = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format( new java.util.Date());
        logger.connectionLog(time + ": opening websocket");
        output = new GraphicOutput();
        output.initializeOutput();
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        String time = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format( new java.util.Date());
        logger.connectionLog(time + ": closing websocket");
        try {
            userSession.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            logger.writeLog(message);
            if(!output.isActive(message)){
                onClose(userSession, new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Player died"));
            }
            this.messageHandler.handleMessage(message);
            output.outputGame(message);
        }
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }


    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }


    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
    public interface MessageHandler {
        void handleMessage(String message);
    }
}


