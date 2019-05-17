package com.example.demo;

import com.example.demo.socketio.SocketServer2Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Integer serverFlag = new Random().nextInt(100);

    @Autowired
    private SocketServer2Spring socketServer;

    @GetMapping("/hello")
    public String hello() {
        return "hello world, s"+ serverFlag;
    }

    @GetMapping("socketio")
    public String socketio() {
        socketServer.sendMessage("send message from springboot...."+new Random().nextInt(100));
        return "send msg to socket io web client ";
    }
}
