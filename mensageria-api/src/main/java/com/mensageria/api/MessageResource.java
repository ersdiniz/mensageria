package com.mensageria.api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mensageria.api.activemq.ActiveMQSender;
import com.mensageria.api.kafka.KafkaMdbSender;
import com.mensageria.api.kafka.KafkaSender;
import com.mensageria.api.kafka.consumer.KafkaConsumer;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/")
@Stateless
public class MessageResource {

    @Inject
    private ActiveMQSender activeMQSender;
    @Inject
    private KafkaMdbSender kafkaMdbSender;
    @Inject
    private KafkaSender kafkaSender;
    @Inject
    private KafkaConsumer kafkaConsumer;

    @GET
    @Path("activemq")
    public Response sendToActiveMQ() {
        activeMQSender.send();
        return Response.ok().build();
    }

    @GET
    @Path("kafka/v1/send")
    public Response sendToKafkaMdb() {
        kafkaMdbSender.send();
        return Response.ok().build();
    }

    @GET
    @Path("kafka/v2/send")
    public Response sendToKafka() {
        kafkaSender.send();
        return Response.ok().build();
    }

    @GET
    @Path("kafka/v2/consume")
    public Response consume() {
        kafkaConsumer.consume();
        return Response.ok().build();
    }
}