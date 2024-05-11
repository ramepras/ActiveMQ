package com.playbook.activemq.v0.consumer;

import com.playbook.activemq.v0.MessageExtractor;
import com.playbook.activemq.v0.LocalActiveMQ;

import javax.jms.*;

public class MessageQueueConsumer {

    private final LocalActiveMQ localActiveMQ;

    public MessageQueueConsumer() {
        this.localActiveMQ = new LocalActiveMQ();
    }

    public void consumeMessagesFromQueue() {
        try {
            ConnectionFactory connectionFactory = localActiveMQ.createConnectionFactory();
            Connection connection = localActiveMQ.createConnection(connectionFactory);
            Session session = localActiveMQ.createSession(connection);

            MessageConsumer consumer = localActiveMQ.consumeFromDestination(session, "TEST_DESTINATION", message -> {
                try {
                    processMessage(message);
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            });

            // Start the connection
            connection.start();

            // Add shutdown hook
            addShutdownHook(connection, consumer, session);


        } catch (JMSException e) {
            // Handle JMS exceptions
            System.err.println("Error while consuming messages from queue: " + e.getMessage());
        }
    }

    private void processMessage(javax.jms.Message message) throws JMSException {
        String content = MessageExtractor.extractMessageContent(message);
        System.out.println(content);
    }

    private void addShutdownHook(Connection connection, MessageConsumer consumer, Session session) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // Stop connection and close resources
                connection.stop();
                consumer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                // Handle JMS exceptions during shutdown
                System.err.println("Error during shutdown: " + e.getMessage());
            }
        }));
    }
}
