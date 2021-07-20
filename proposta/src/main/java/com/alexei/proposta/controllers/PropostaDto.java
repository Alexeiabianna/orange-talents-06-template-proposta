package com.alexei.proposta.controllers;

import com.alexei.proposta.models.Proposta;

public class PropostaDto {

    private String documento;
    private String nome;
    private Long idProposta;

    public PropostaDto(Proposta proposta) {
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
