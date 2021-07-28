package com.alexei.proposta.controllers.form;

import javax.validation.constraints.NotBlank;

import com.alexei.proposta.models.Biometria;
import com.alexei.proposta.models.Proposta;

public class BiometriaForm {

    @NotBlank
    private String fingerPrint;

    public BiometriaForm() {
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Biometria toModel(Proposta proposta) {
        return new Biometria(fingerPrint, proposta);
    }
}
