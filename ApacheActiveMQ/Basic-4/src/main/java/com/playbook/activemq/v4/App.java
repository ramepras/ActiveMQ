package com.playbook.activemq.v4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Scheduled(fixedDelay = 5000) // Run every 5 seconds
    public void consumeMessage() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9090/consume"; // Replace with the actual URL of the web service
        String message = restTemplate.postForObject(url, null, String.class);
        System.out.println("Received message: " + message);
    }
}
