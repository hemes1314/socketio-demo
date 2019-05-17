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

public abstract class SocketServer {

    protected static Map<String, UUID> requestType2SessionId = new ConcurrentHashMap<>();

    protected abstract void setSocketIOServer(SocketIOServer server);

    public void socketioInit() throws Exception {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(7777);

        SocketIOServer server = new SocketIOServer(config);
        setSocketIOServer(server);

        server.addConnectListener(new ConnectListener(){
            @Override
            public void onConnect(SocketIOClient client) {
                String requestType = client.getHandshakeData().getSingleUrlParam("requestType");
                requestType2SessionId.put(requestType, client.getSessionId());
                System.out.println("requestType:"+requestType+",client session id:"+client.getSessionId());
            }
        });

        CharteventListener listner = new CharteventListener();
        listner.setServer(server);
        server.addEventListener("chatEvent", ChatObject.class, listner);

        server.addEventListener("chatevent", String.class, new DataListener() {

            @Override
            public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
                System.out.println(client.getSessionId()+":"+data+",client id:"+requestType2SessionId.get("chat-web"));
                server.getClient(requestType2SessionId.get("chat-web")).sendEvent("chatevent", new ChatObject("xxx","hello web client..."));
            }
        });
        //启动服务
        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }

    public static void main(String[] args) throws Exception {

        new SocketServer() {
            @Override
            protected void setSocketIOServer(SocketIOServer server) {
                return;
            }
        }.socketioInit();
    }
}