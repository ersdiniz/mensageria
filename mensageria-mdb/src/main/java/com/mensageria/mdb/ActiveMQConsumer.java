package com.mensageria.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

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
            System.out.println(String.format("##### Mensagem consumida no ActiveMQ: %s #####", ((TextMessage) message).getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}