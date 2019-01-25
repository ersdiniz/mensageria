package com.mensageria.api.activemq.consumer;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

import com.mensageria.api.activemq.ActiveMQWebSocket;
import com.mensageria.util.DateTimeUtil;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "ActiveMQTesteQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "transactionTimeout", propertyValue = "600"),
        @ActivationConfigProperty(propertyName = "maxSessions", propertyValue = "1")
})
@ResourceAdapter("activemq-rar.rar")
public class ActiveMQConsumer implements MessageListener {

    @Override
    public void onMessage(final Message message) {
        try {
            final String mensagem = ((TextMessage) message).getText();

            System.out.println(String.format("##### Mensagem consumida no ActiveMQ: %s #####", mensagem));

            ActiveMQWebSocket.getSessions().forEach(session -> {
                try {
                    session.getBasicRemote().sendText("Mensagem consumida às " + DateTimeUtil.format(LocalDateTime.now()) + ": " + mensagem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}