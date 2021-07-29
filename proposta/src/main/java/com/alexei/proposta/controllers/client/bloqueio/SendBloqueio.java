package com.alexei.proposta.controllers.client.bloqueio;

public class SendBloqueio {

    private String sistemaResponsavel;

    public SendBloqueio(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

}
