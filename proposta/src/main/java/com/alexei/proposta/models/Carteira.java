package com.alexei.proposta.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String idCartao;
    private String email;
    private String carteira;

    @ManyToOne
    private Proposta proposta;

    public Carteira(String email, String carteira, Proposta proposta) {
        this.idCartao = proposta.getCartao();
        this.email = email;
        this.carteira = carteira;
        this.proposta = proposta;
    }

    public String getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public boolean isValidId() {
        if(idCartao != null)
            return true;
        return false;
    }

}
