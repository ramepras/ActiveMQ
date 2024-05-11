package com.playbook.activemq.v0;

import javax.jms.*;

public class MessageExtractor {

    public static String extractMessageContent(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            return extractTextMessageContent((TextMessage) message);
        } else if (message instanceof BytesMessage) {
            return extractBytesMessageContent((BytesMessage) message);
        } else if (message instanceof MapMessage) {
            return extractMapMessageContent((MapMessage) message);
        } else if (message instanceof StreamMessage) {
            return extractStreamMessageContent((StreamMessage) message);
        } else if (message instanceof ObjectMessage) {
            return extractObjectMessageContent((ObjectMessage) message);
        } else {
            return "Unsupported message type";
        }
    }

    private static String extractTextMessageContent(TextMessage textMessage) throws JMSException {
        return textMessage.getText();
    }

    private static String extractBytesMessageContent(BytesMessage bytesMessage) throws JMSException {
        byte[] bytes = new byte[(int) bytesMessage.getBodyLength()];
        bytesMessage.readBytes(bytes);
        return new String(bytes);
    }

    private static String extractMapMessageContent(MapMessage mapMessage) throws JMSException {
        // Assuming you know the key
        return mapMessage.getString("key");
    }

    private static String extractStreamMessageContent(StreamMessage streamMessage) throws JMSException {
        // Assuming you know the order of elements
        return streamMessage.readString();
    }

    private static String extractObjectMessageContent(ObjectMessage objectMessage) throws JMSException {
        // Assuming the object is Serializable
        Object object = objectMessage.getObject();
        return object.toString();
    }
}
