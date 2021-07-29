package com.alexei.proposta.controllers;

import javax.validation.constraints.NotBlank;

import com.alexei.proposta.models.BloqueioCartao;
import com.alexei.proposta.models.Proposta;

public class BloqueioForm {

    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgente;

    @Deprecated
    public BloqueioForm() {
    }

    public BloqueioForm(String userAgent, String ipClient) {
        this.userAgente = userAgent;
        this.ipCliente = ipClient;
    } 

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgente() {
        return userAgente;
    }

    public void setUserAgente(String userAgente) {
        this.userAgente = userAgente;
    }

    public BloqueioCartao toModel(Proposta proposta) {
        return new BloqueioCartao(ipCliente, userAgente, proposta);
    }

}
