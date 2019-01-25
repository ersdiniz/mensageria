package com.mensageria.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MensageriaDto implements Serializable {

    private String mensagem;

    public MensageriaDto() {
    }

    private MensageriaDto(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem == null ? "<Empty>" : mensagem;
    }

    public static MensageriaDto of(final String mensagem) {
        return new MensageriaDto(mensagem);
    }
}