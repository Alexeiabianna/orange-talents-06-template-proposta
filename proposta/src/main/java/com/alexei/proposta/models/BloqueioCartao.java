package com.alexei.proposta.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipCliente;
    private String userAgente;
    private LocalDateTime dataCriacao;

    @ManyToOne
    private Proposta proposta;
    private String idCartao;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(String ipCliente, String userAgente, Proposta proposta) {
        this.ipCliente = ipCliente;
        this.userAgente = userAgente;
        this.dataCriacao = LocalDateTime.now();
        this.idCartao = proposta.getCartao();
        this.proposta = proposta;
    }

    public Long getId() {
        return id;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgente() {
        return userAgente;
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
