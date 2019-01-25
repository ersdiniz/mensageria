package com.mensageria.api.kafka.consumer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import com.mensageria.api.kafka.KafkaWebSocket;
import com.mensageria.util.DateTimeUtil;

@KafkaConfig(bootstrapServers = "localhost:9092")
public class KafkaMdbConsumer {

    @Consumer(topics = "topic-kafka-v1", groupId = "myGroupID")
    public void test(final String key, final String value) {
        System.out.println(String.format("##### Mensagem consumida no MDB do Kafka: %s #####", value));

        KafkaWebSocket.getSessions().forEach(session -> {
            try {
                session.getBasicRemote().sendText("Mensagem consumida às " + DateTimeUtil.format(LocalDateTime.now()) + ": " + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}