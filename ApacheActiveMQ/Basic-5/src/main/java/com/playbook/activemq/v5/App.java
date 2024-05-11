package com.playbook.activemq.v5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Scheduled(fixedDelay = 5000) // Run every 5 seconds
    public void publishMessage() {
        Date date = new Date();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9090/publish"; // Replace with the actual URL of the web service
        String message = "Hello from Publisher: " + date.toString(); // Replace with the message you want to publish
        restTemplate.postForObject(url, message, String.class);
        System.out.println("Message published: " + message);
    }
}
