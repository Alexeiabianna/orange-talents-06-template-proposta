package com.alexei.proposta.controllers;

import java.math.BigDecimal;

public class PropostaForm {

    private String cpfORcnpj;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    public String getCpfORcnpj() {
        return cpfORcnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

}
