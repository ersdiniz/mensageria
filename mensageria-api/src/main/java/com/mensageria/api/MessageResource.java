package com.mensageria.api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mensageria.api.kafka.KafkaSender;
import com.mensageria.api.kafka.consumer.KafkaConsumer;
import com.mensageria.dto.MensageriaDto;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/")
@Stateless
public class MessageResource {

    @Inject
    private KafkaSender kafkaSender;
    @Inject
    private KafkaConsumer kafkaConsumer;

    @POST
    @Path("kafka/v2/send")
    public Response sendToKafka(final MensageriaDto dto) {
        kafkaSender.send(dto.getMensagem());
        return Response.ok().build();
    }

    @GET
    @Path("kafka/v2/consume")
    public Response consume() {
        return Response.ok(MensageriaDto.of(kafkaConsumer.consume())).build();
    }
}