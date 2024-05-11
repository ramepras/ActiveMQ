package com.playbook.activemq.v0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicOneApp {
    private static final Logger logger = LoggerFactory.getLogger(BasicOneApp.class);

    public static void main(String[] args) {
        try {
            MyActiveMQ activeMQ = new MyActiveMQ();
            activeMQ.publish("TEST_DESTINATION", "While watching Netflix");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


