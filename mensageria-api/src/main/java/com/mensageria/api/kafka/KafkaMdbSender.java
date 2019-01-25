package com.mensageria.api.kafka;

import java.time.LocalDateTime;

import javax.ejb.Stateless;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import com.mensageria.util.DateTimeUtil;

@Stateless
@KafkaConfig(bootstrapServers = "localhost:9092")
public class KafkaMdbSender {

    private static final String TOPIC = "topic-kafka-v1";

    @Producer
    private SimpleKafkaProducer<String, String> producer;

    public void send(final String mensagem) {
        final String message = mensagem + " - " + DateTimeUtil.format(LocalDateTime.now());

        producer.send(TOPIC, message);

        System.out.println(String.format("##### Mensagem enviada para o MDB do Kafka: %s #####", message));
    }
}