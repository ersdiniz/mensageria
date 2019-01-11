package com.mensageria.api.activemq;

import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.mensageria.util.DateTimeUtil;

@Stateless
public class ActiveMQSender {

    private static final String QUEUE = "ActiveMQTesteQueue";

    public void send() {
        try {
            final Connection connection = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL).createConnection();

            connection.start();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            final TextMessage message = session.createTextMessage(DateTimeUtil.format(LocalDateTime.now()));

            session.createProducer(session.createQueue(QUEUE)).send(message);

            System.out.println(String.format("##### Mensagem enviada para ActiveMQ: %s #####", message.getText()));

            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}