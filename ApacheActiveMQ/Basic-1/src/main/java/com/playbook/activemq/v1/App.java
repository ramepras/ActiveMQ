package com.playbook.activemq.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            ActiveMQ activeMQ = new ActiveMQ();
            activeMQ.publish("TEST_DESTINATION", "Finished watching Suits on Netflix");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
