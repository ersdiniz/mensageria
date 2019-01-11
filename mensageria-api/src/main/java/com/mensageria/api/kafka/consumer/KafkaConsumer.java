package com.mensageria.api.kafka.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import javax.ejb.Stateless;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

@Stateless
public class KafkaConsumer {

    private static final String TOPIC = "topic-kafka-v2";

    public void consume() {
        final Consumer<String, String> consumer = createConsumer();
        try {
            final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));

            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("##### Mensagem consumida no Kafka: %s #####", record.value()));
            }
        } finally {
            consumer.close();
        }
    }

    private Consumer<String, String> createConsumer() {
        final Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroup1");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        final Consumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }
}