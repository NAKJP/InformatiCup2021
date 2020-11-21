package com.company;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import javax.websocket.*;

@ClientEndpoint
public class Websocket{

    private Session userSession;
    private GraphicOutput output;
    private MessageHandler messageHandler;
    private Log logger = new Log();
    private ClientMessage clientMessage ;
    private ServerMessage serverMessage ;
    private String time = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new java.util.Date());

    public Websocket (URI endpointURI){
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
            this.messageHandler = new MessageHandler() {
                @Override
                public void handleMessage(String message) {

                }
            };
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        logger.connectionLog(this.time + ": opening websocket");
        this.userSession = userSession;
        output = new GraphicOutput();
        output.initializeOutput();
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.connectionLog(this.time + ": closing websocket");
        try {
            this.userSession = userSession;
            this.userSession.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            serverMessage = new Gson().fromJson(message, ServerMessage.class);

            System.out.println("Player 1 is " + serverMessage.getPlayers().getPlayer1().getActive());
            System.out.println("Your Playernumber: "+ serverMessage.getYou());

            logger.writeLog(message);
            sendMessage("{\"action\": \"change_nothing\"}");
            if(!output.isActive(message)){
                onClose(userSession, new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Player died"));
            }
            this.messageHandler.handleMessage(message);
            output.outputGame(message);
        }
    }

    public void sendMessage(String clientMessage) {
        this.userSession.getAsyncRemote().sendObject(clientMessage);
    }
}


