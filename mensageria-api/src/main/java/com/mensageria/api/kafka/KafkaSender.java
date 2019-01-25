package com.mensageria.api.kafka;

import java.time.LocalDateTime;
import java.util.Properties;

import javax.ejb.Stateless;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.mensageria.util.DateTimeUtil;

@Stateless
public class KafkaSender {

    private static final String TOPIC = "topic-kafka-v2";

    public void send(final String mensagem) {
        final String message = mensagem + " - " + DateTimeUtil.format(LocalDateTime.now());

        final Producer<String, String> producer = createProducer();
        try {
            producer.send(new ProducerRecord<String, String>(TOPIC, message));

            System.out.println(String.format("##### Mensagem enviada para o Kafka: %s #####", message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    private Producer<String, String> createProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(properties);
    }
}