package com.alexei.proposta.controllers.client.carteira;

public class SendCarteira {
    private String email;
    private String carteira;

    public SendCarteira(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

}
