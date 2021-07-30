package com.alexei.proposta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idCartao;
    private String destino;
    private LocalDate dataTermino;
    private LocalDateTime dataCriacao;
    private String ipCliente;
    private String userAgente;

    @ManyToOne
    private Proposta proposta;

    public AvisoViagem(String destino, LocalDate dataTermino, String ipCliente, String userAgente, Proposta proposta) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipCliente = ipCliente;
        this.userAgente = userAgente;
        this.dataCriacao = LocalDateTime.now();
        this.idCartao = proposta.getCartao();
    }

    public Long getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgente() {
        return userAgente;
    }

    public Proposta getProposta() {
        return proposta;
    }

}
