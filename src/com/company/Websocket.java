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
    private Me myPlayer;
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
    public void onClose(Session userSession) {
        try {
            logger.connectionLog("closing websocket");
            this.userSession = userSession;
            this.userSession.close();
            //Runtime.getRuntime().exit(1);

        }
        catch (IOException e){
            logger.connectionLog("Closing FAILED");
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            serverMessage = new Gson().fromJson(message, ServerMessage.class);
            myPlayer = new Me(getMe());

            logger.writeLog(message);
            output.outputGame(message);
            System.out.println("Your Playernumber: "+ serverMessage.getYou());

            if(getMe().getActive()){
                sendMessage();
            }
            else {
                logger.connectionLog("You already died");
            }
        }
    }

    public void sendMessage() {
        String response = clientMessage.randomResponse(serverMessage.getCells(), myPlayer);
        System.out.println(response);
        String clientMsg = "{\"action\": \""+response+"\"}";
        this.userSession.getAsyncRemote().sendObject(clientMsg);
    }

    public ServerMessage.Players.Player getMe(){
        int me = serverMessage.getYou();
        switch(me){
            case 1: return serverMessage.getPlayers().getPlayer1();
            case 2: return serverMessage.getPlayers().getPlayer2();
            case 3: return serverMessage.getPlayers().getPlayer3();
            case 4: return serverMessage.getPlayers().getPlayer4();
            case 5: return serverMessage.getPlayers().getPlayer5();
            default: return serverMessage.getPlayers().getPlayer6();
        }
    }

    public Me getMyPlayer(){
        return myPlayer;
    }
}


