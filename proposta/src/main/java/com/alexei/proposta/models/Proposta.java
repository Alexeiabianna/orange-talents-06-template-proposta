package com.alexei.proposta.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alexei.proposta.controllers.client.documento.StatusCliente;

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

    @Enumerated(EnumType.STRING)
    private StatusProposta status;

    private String cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String cpfORcnpj, String email, String nome, BigDecimal salario, String rua, String cep,
            String bairro, String numero, String complemento, String cidade, StatusCliente status) {
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
        this.status = setStatusProposta(status);

    }

    public void addCartao(String cartao) {
        this.cartao = cartao;
    }

    private StatusProposta setStatusProposta(StatusCliente status) {
        if (status.equals(StatusCliente.COM_RESTRICAO)) {
            return StatusProposta.NAO_ELEGIVEL;
        }
        if (status.equals(StatusCliente.SEM_RESTRICAO)) {
            return StatusProposta.ELEGIVEL;
        }
        return StatusProposta.EM_ANALISE;
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

    public String getCartao() {
        return cartao;
    }

    public StatusProposta getStatusProposta() {
        return status;
    }

}
