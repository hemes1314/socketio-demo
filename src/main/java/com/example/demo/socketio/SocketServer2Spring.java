package com.example.demo.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketServer2Spring extends SocketServer implements CommandLineRunner {

    @Autowired
    private SocketServer2Spring socketServer;

    private SocketIOServer socketIOServer;

    public void sendMessage(String msg) {
        socketIOServer.getClient(requestType2SessionId.get("chat-web")).sendEvent("chatevent", new ChatObject("xxx", msg));
    }

    @Override
    public void run(String... args) throws Exception {
        socketServer.socketioInit();
    }

    @Override
    public void setSocketIOServer(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }
}