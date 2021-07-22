package com.alexei.proposta.controllers.client.cartao;

public class CartaoResposta {

    String id;
    String emitidoEm;
    String titular;

    public CartaoResposta(String id, String emitidoEm, String titular) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
    }

    public String getId() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

}
