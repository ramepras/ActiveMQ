package com.playbook.activemq.v0;

import com.playbook.activemq.v0.consumer.MessageQueueConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class TestLocalActiveMQ {
    private final Logger logger = LoggerFactory.getLogger(TestLocalActiveMQ.class);

    public void testCreateConnectionFactory() {
        LocalActiveMQ activeMQ = new LocalActiveMQ();
        ConnectionFactory connectionFactory = activeMQ.createConnectionFactory();
        if (connectionFactory != null) {
            logger.info(String.format("Connection factory created successfully."));
            // You can further test the connection factory here, e.g., create a connection and test it.
        } else {
            logger.info(String.format("Failed to create connection factory."));
        }
    }

    public void testSendTextMessageToQueueUsingSession() throws JMSException {
        LocalActiveMQ localActiveMQ = new LocalActiveMQ();
        ConnectionFactory connectionFactory = localActiveMQ.createConnectionFactory();
        Connection connection = localActiveMQ.createConnection(connectionFactory);
        Session session = localActiveMQ.createSession(connection);
        localActiveMQ.sendTextMessageToQueueUsingSession("This is a test message using session", session);
        session.close();
        connection.close();

    }

    public void testSendTextMessageToQueueUsingQueueSession() throws JMSException {
        LocalActiveMQ localActiveMQ = new LocalActiveMQ();
        QueueConnectionFactory queueConnectionFactory = localActiveMQ.createQueueConnectionFactory();
        QueueConnection queueConnection = localActiveMQ.createQueueConnection(queueConnectionFactory);
        QueueSession queueSession = localActiveMQ.createQueueSession(queueConnection);
        localActiveMQ.sendTextMessageToQueueUsingQueueSession("Another message using queue session", queueSession);
        queueSession.close();
        queueConnection.close();

    }

    public void textSendTextMessageToTopicUsingTopicSession() throws JMSException {
        LocalActiveMQ localActiveMQ = new LocalActiveMQ();
        TopicConnectionFactory topicConnectionFactory = localActiveMQ.createTopicConnectionFactory();
        TopicConnection topicConnection = localActiveMQ.createTopicConnection(topicConnectionFactory);
        TopicSession topicSession = localActiveMQ.createTopicSession(topicConnection);
        localActiveMQ.sendTextMessageToTopicUsingTopicSession("This message is sent to topic using topic session", topicSession);
        topicSession.close();
        topicConnection.close();
    }

    public void testConsumeFromQueue(boolean useClass) {
        MessageQueueConsumer messageQueueConsumer = new MessageQueueConsumer();
        messageQueueConsumer.consumeMessagesFromQueue();
    }

    public void testConsumeFromQueue() throws JMSException {
        LocalActiveMQ localActiveMQ = new LocalActiveMQ();
        ConnectionFactory connectionFactory = localActiveMQ.createConnectionFactory();
        Connection connection = localActiveMQ.createConnection(connectionFactory);
        Session session = localActiveMQ.createSession(connection);
        MessageConsumer consumer = localActiveMQ.consumeFromDestination(session, "TEST_DESTINATION", (message) -> {
            System.out.println(message);

        });
        connection.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    connection.stop();
                    super.run();
                    consumer.close();
                    session.close();
                    connection.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
