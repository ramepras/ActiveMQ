package com.playbook.activemq.v0;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class LocalActiveMQ {
    public final String BROKER_URL = "tcp://localhost:61616";
    private final Logger logger = LoggerFactory.getLogger(LocalActiveMQ.class);

    public ConnectionFactory createConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        return activeMQConnectionFactory;
    }

    public XAConnectionFactory createXAConnectionFactory() {
        ActiveMQXAConnectionFactory activeMQConnectionFactory = new ActiveMQXAConnectionFactory(BROKER_URL);
        return activeMQConnectionFactory;
    }

    public QueueConnectionFactory createQueueConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        return activeMQConnectionFactory;
    }

    public XAQueueConnectionFactory createXAQueueConnectionFactory() {
        ActiveMQXAConnectionFactory activeMQXAConnectionFactory = new ActiveMQXAConnectionFactory(BROKER_URL);
        return activeMQXAConnectionFactory;
    }

    public TopicConnectionFactory createTopicConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        return activeMQConnectionFactory;
    }

    public XATopicConnectionFactory createXATopicConnectionFactory() {
        ActiveMQXAConnectionFactory activeMQXAConnectionFactory = new ActiveMQXAConnectionFactory(BROKER_URL);
        return activeMQXAConnectionFactory;
    }

    public Connection createConnection(ConnectionFactory connectionFactory) throws JMSException {
        return connectionFactory.createConnection();
    }

    public QueueConnection createQueueConnection(QueueConnectionFactory queueConnectionFactory) throws JMSException {
        return queueConnectionFactory.createQueueConnection();
    }

    public TopicConnection createTopicConnection(TopicConnectionFactory topicConnectionFactory) throws JMSException {
        return topicConnectionFactory.createTopicConnection();
    }

    public XAConnection createXAConnection(XAConnectionFactory xaConnectionFactory) throws JMSException {
        return xaConnectionFactory.createXAConnection();
    }

    public XAQueueConnection createXAQueueConnection(XAQueueConnectionFactory xaQueueConnectionFactory) throws JMSException {
        return xaQueueConnectionFactory.createXAQueueConnection();
    }

    public XATopicConnection createXATopicConnection(XATopicConnectionFactory xaTopicConnectionFactory) throws JMSException {
        return xaTopicConnectionFactory.createXATopicConnection();
    }

    public Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public QueueSession createQueueSession(QueueConnection queueConnection) throws JMSException {
        return queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public TopicSession createTopicSession(TopicConnection topicConnection) throws JMSException {
        return topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    // XA version of Session is also available.

    public void sendTextMessageToQueueUsingSession(String message, Session session) throws JMSException {
        Queue queue = session.createQueue("TEST_DESTINATION");
        TextMessage textMessage = session.createTextMessage(message);
        MessageProducer producer = session.createProducer(queue);  // create producer
        producer.send(textMessage);
    }

    public void sendTextMessageToQueueUsingQueueSession(String message, QueueSession queueSession) throws JMSException {
        Queue queue = queueSession.createQueue("TEST_DESTINATION");
        TextMessage textMessage = queueSession.createTextMessage(message);
        MessageProducer producer = queueSession.createSender(queue); // create sender
        producer.send(textMessage);
    }

    public void sendTextMessageToTopicUsingTopicSession(String message, TopicSession topicSession) throws JMSException {
        Topic topic = topicSession.createTopic("TEST_TOPIC");
        TextMessage textMessage = topicSession.createTextMessage(message);
        TopicPublisher publisher = topicSession.createPublisher(topic); // create publisher
        publisher.send(textMessage);
    }

    public MessageConsumer consumeFromDestination(Session session, String destination, MessageListener messageListener) throws JMSException {
        Queue queue = session.createQueue(destination);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(messageListener);
        return consumer;
    }
}
