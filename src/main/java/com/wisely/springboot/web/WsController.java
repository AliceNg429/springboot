package com.wisely.springboot.web;

import com.wisely.springboot.domain.WiselyMessage;
import com.wisely.springboot.domain.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
    //当浏览器向服务端发送请求时，通过@MessageMapping
    //映射/welcome这个地址，类似于@RequestMapping。
    @MessageMapping("/welcome") //1

    //②当服务端有消息时，会对订阅了@SendTo中的路径的浏
    //览器发送消息。
    @SendTo("/topic/getResponse") //2
    public WiselyResponse say(WiselyMessage message) throws Exception {
        Thread.sleep(3000);
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }
}