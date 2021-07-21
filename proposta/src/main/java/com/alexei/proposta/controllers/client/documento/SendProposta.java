package com.alexei.proposta.controllers.client.documento;

import com.alexei.proposta.models.Proposta;

public class SendProposta {

    private String documento;
    private String nome;
    private Long idProposta;

    public SendProposta(Proposta proposta) {
        this.documento = proposta.getCpfORcnpj();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }    

}
