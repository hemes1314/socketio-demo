package com.example.demo.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

import java.util.UUID;

public class CharteventListener implements DataListener<ChatObject> {

    public static UUID sessionId;

    private SocketIOServer server;

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    public void onData(SocketIOClient client, ChatObject data,
                       AckRequest ackSender) throws Exception {
        System.out.println(data);
        sessionId = client.getSessionId();
        this.server.getBroadcastOperations().sendEvent("chatevent", data);
    }

}