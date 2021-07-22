package com.alexei.proposta.controllers.dto;

import java.math.BigDecimal;

import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.models.StatusProposta;

public class SituacaoPropostaDto {

    private String cpfORcnpj;
    private String email;
    private String nome;
    private BigDecimal salario;
    private StatusProposta status;
    private String cartao;

    public SituacaoPropostaDto(Proposta proposta) {
        this.cpfORcnpj = proposta.getCpfORcnpj();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatusProposta();
        this.cartao = proposta.getCartao();
    }

    public String getCpfORcnpj() {
        return cpfORcnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public String getCartao() {
        return cartao;
    }

}
