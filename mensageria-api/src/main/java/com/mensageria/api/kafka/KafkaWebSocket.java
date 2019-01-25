package com.mensageria.api.kafka;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/kafka")
public class KafkaWebSocket {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @Inject
    private KafkaMdbSender kafkaMdbSender;

    @OnMessage
    public void onMessage(final String message, final Session session) throws IOException {
        kafkaMdbSender.send(message);
    }

    @OnOpen
    public void onOpen(final Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(final Session session) {
        sessions.remove(session);
    }

    public static Set<Session> getSessions() {
        return sessions;
    }
}