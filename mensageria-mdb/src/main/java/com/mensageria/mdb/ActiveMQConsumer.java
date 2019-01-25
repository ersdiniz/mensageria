package com.mensageria.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

import com.mensageria.dto.MensageriaDto;

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
            final MensageriaDto dto = (MensageriaDto) ((ObjectMessage) message).getObject();

            System.out.println(String.format("##### Mensagem consumida no ActiveMQ: %s #####", dto.getMensagem()));

            dto.getSession().getBasicRemote().sendText(dto.getMensagem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}