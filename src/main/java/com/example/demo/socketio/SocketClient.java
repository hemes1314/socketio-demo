package com.example.demo.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;

public class SocketClient {
    public static void main(String[] args) throws Exception {

        String url = "http://localhost:7777?requestType=chat-java";
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;
        options.reconnectionDelay = 1000;
        options.timeout = 500;
        final Socket socket = IO.socket(url, options);

        socket.connect();
        while (true) {
            socket.emit("chatevent", "send message test from java");
            System.out.println("send to "+ url + ": "+"send message test from java");
            Thread.sleep(1000);
        }
    }
}
