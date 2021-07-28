package com.alexei.proposta.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fingerPrint;
    private LocalDateTime dataCriacao;
    private String idCartao;

    @ManyToOne
    private Proposta proposta;

    public Biometria(String fingerPrint, Proposta proposta) {
        this.fingerPrint = fingerPrint;
        this.proposta = proposta;
        this.dataCriacao = LocalDateTime.now();
        this.idCartao = proposta.getCartao();
    }

    public Long getId() {
        return id;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public Proposta getProposta() {
        return proposta;
    }

}
