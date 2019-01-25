package com.mensageria.dto;

import java.io.Serializable;

import javax.websocket.Session;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MensageriaDto implements Serializable {

    private Session session;
    private String mensagem;

    public MensageriaDto() {
    }

    private MensageriaDto(final Session session, final String mensagem) {
        this.session = session;
        this.mensagem = mensagem;
    }

    public Session getSession() {
        return session;
    }

    public String getMensagem() {
        return mensagem == null ? "<Empty>" : mensagem;
    }

    public static MensageriaDto of(final String mensagem) {
        return new MensageriaDto(null, mensagem);
    }

    public static MensageriaDto of(final Session session, final String mensagem) {
        return new MensageriaDto(session, mensagem);
    }
}