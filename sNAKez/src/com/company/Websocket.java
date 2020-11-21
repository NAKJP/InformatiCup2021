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
    private ClientMessage clientMessage = new ClientMessage();
    private Log logger = new Log();
    private ServerMessage serverMessage ;
    private String time = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(new java.util.Date());

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
        logger.connectionLog("connected websocket");
        this.userSession = userSession;
        output = new GraphicOutput();
        output.initializeOutput();
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.connectionLog("closing websocket");
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

            logger.writeLog(message);
            output.outputGame(message);
            System.out.println("Your Playernumber: "+ serverMessage.getYou());

            sendMessage();
            if(!output.isActive(message)){
                onClose(userSession, new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Player died"));
            }
        }
    }

    public void sendMessage() {
        String response = clientMessage.randomResponse();
        System.out.println(response);
        String clientMsg = "{\"action\": \""+response+"\"}";
        this.userSession.getAsyncRemote().sendObject(clientMsg);
    }
}


