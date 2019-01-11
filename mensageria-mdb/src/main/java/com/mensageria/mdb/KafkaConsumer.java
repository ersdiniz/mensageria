package com.mensageria.mdb;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

@KafkaConfig(bootstrapServers = "localhost:9092")
public class KafkaConsumer {

    @Consumer(topics = "topic-kafka-v1", groupId = "myGroupID")
    public void test(final String key, final String value) {
        System.out.println(String.format("##### Mensagem consumida no MDB do Kafka: %s #####", value));
    }
}