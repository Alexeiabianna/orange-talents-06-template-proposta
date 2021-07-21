package com.alexei.proposta.controllers.form;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.alexei.proposta.controllers.client.documento.StatusCliente;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.validation.CPForCNPJ;

public class PropostaForm {

    @NotBlank
    @CPForCNPJ
    private String cpfORcnpj;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal salario;

    @NotBlank
    @Size(max = 40)
    private String rua;
    @NotBlank
    @Size(max = 20)
    private String cep;
    @NotBlank
    @Size(max = 20)
    private String bairro;
    @NotBlank
    @Size(max = 10)
    private String numero;
    @NotBlank
    @Size(max = 30)
    private String complemento;
    @NotBlank
    @Size(max = 40)
    private String cidade;

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

    public Proposta toModel(StatusCliente status) {
        return new Proposta(this.cpfORcnpj, this.email, this.nome, this.salario, this.rua, this.cep, this.bairro,
                this.numero, this.complemento, this.cidade, status);
    }
}
