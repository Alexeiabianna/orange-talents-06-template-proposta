package com.alexei.proposta.controllers.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.alexei.proposta.models.Carteira;
import com.alexei.proposta.models.Proposta;

public class CarteiraForm {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String carteira;

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public Carteira toModel(Proposta proposta) {
        return new Carteira(email, carteira, proposta);
    }

}
