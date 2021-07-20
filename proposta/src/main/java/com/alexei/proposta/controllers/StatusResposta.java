package com.alexei.proposta.controllers;

public class StatusResposta {

    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusCliente getResultadoSolicitacao() {
        if(resultadoSolicitacao.equals("SEM_RESTRICAO"))
            return StatusCliente.SEM_RESTRICAO;
        return StatusCliente.COM_RESTRICAO;
    }

    public String getIdProposta() {
        return idProposta;
    }

}
