package com.alexei.proposta.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfORcnpj;
    private String email;
    private String nome;
    private BigDecimal salario;
    private String rua;
    private String cep;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String cpfORcnpj, String email, String nome, BigDecimal salario, String rua, String cep,
            String bairro, String numero, String complemento, String cidade) {
        this.cpfORcnpj = cpfORcnpj;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
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

    public String getRua() {
        return rua;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

}
