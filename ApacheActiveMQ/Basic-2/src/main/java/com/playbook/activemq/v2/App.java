package com.playbook.activemq.v2;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(ConnectionFactory connectionFactory) {
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate(CachingConnectionFactory cachingConnectionFactory) {
        return new JmsTemplate(cachingConnectionFactory);
    }
}

@RestController
class MessageController {

    private final JmsTemplate jmsTemplate;

    public MessageController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody String message) {
        jmsTemplate.convertAndSend("exampleQueue", message);
        return "Message published to ActiveMQ: " + message;
    }

    @PostMapping("/consume")
    public String consumeMessage() {
        String receivedMessage = (String) jmsTemplate.receiveAndConvert("exampleQueue");
        return "Received message from ActiveMQ: " + receivedMessage;
    }
}
