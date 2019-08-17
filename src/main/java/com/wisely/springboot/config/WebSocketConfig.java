package com.wisely.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//（1）配置WebSocket，需要在配置类上使用
//@EnableWebSocketMessageBroker开启WebSocket支持，并通
//过继承AbstractWebSocketMessageBrokerConfigurer类，重写其
//方法来配置WebSocket。

@Configuration
//通过@EnableWebSocketMessageBroker注解开启使用
//STOMP协议来传输基于代理（message broker）的消息，这时
//控制器支持使用@MessageMapping，就像使用
//@RequestMapping一样。
@EnableWebSocketMessageBroker//1
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
    @Override
    //注册STOMP协议的节点（endpoint），并映射的指定的URL。
    public void registerStompEndpoints(StompEndpointRegistry registry) { //2
        //注册一个STOMP的endpoint，并指定使用SockJS协议。
        registry.addEndpoint("/endpointWisely").withSockJS(); //3
    }
    @Override
    //配置消息代理（Message Broker）。
    public void configureMessageBroker(MessageBrokerRegistry registry) {//4
        //广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic"); //5
    }
}