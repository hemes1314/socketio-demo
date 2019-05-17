package com.example.demo;

import com.example.demo.socketio.CharteventListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Integer flag = new Random().nextInt(100);

    @GetMapping("/hello")
    public String hello() {
        return "hello world, s"+ flag;
    }

}
