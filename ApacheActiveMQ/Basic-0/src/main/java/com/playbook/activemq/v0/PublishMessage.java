package com.playbook.activemq.v0;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PublishMessage {
    public void publish(String destinationName, String message) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(destinationName);
        MessageProducer producer = session.createProducer(destination);
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
        connection.close();
    }
}
