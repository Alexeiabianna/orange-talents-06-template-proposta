package com.alexei.proposta.controllers.form;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alexei.proposta.models.AvisoViagem;
import com.alexei.proposta.models.Proposta;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AvisoViagemForm {

    @NotBlank
    private String destino;
    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    public AvisoViagemForm(){}

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public AvisoViagem toModel(String userAgente, String ipCliente, Proposta proposta) {
        return new AvisoViagem(destino, dataTermino, ipCliente, userAgente, proposta);
    }

}
