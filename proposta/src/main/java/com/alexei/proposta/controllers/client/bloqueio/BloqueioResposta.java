package com.alexei.proposta.controllers.client.bloqueio;

public class BloqueioResposta {

    private String resultado;

    
    public BloqueioResposta() {
    }

    public String getResultado() {
        return resultado;
    }

    public boolean isValid() {
        if(resultado.equals("BLOQUEADO")) {
            System.out.println("Resultado request: "+resultado);
            return true;
        }
        return false;
    }    

}
