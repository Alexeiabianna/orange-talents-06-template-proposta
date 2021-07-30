package com.alexei.proposta.controllers.client.carteira;

public class AssociaResposta {
    private String resultado;
    private String id;

    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public boolean isValid() {
        return resultado.equals("ASSOCIADA");
    }

}
