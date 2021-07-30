package com.alexei.proposta.controllers.client.bloqueio;

public class BloqueioResposta {

    private String resultado;

    public BloqueioResposta() {
    }

    public String getResultado() {
        return resultado;
    }

    public boolean isValid() {
        if (resultado.equals("BLOQUEADO")) {
            return true;
        }
        return false;
    }

}
