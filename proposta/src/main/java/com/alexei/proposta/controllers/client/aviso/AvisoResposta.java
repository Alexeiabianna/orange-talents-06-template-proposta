package com.alexei.proposta.controllers.client.aviso;

public class AvisoResposta {
    public String resultado;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public boolean isValid() {
        return resultado.equals("CRIADO");
    }
}
