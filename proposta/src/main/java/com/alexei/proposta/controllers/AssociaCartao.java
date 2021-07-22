package com.alexei.proposta.controllers;

import java.util.Collection;

import com.alexei.proposta.controllers.Logs.LoggerSolicitaCartao;
import com.alexei.proposta.controllers.client.cartao.CartaoResposta;
import com.alexei.proposta.controllers.client.cartao.SolicitaCartao;
import com.alexei.proposta.controllers.client.documento.SendProposta;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.models.StatusProposta;
import com.alexei.proposta.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AssociaCartao {

    private SolicitaCartao resultadoCartao;
    private LoggerSolicitaCartao loggerSolicitaCartao;
    private PropostaRepository propostaRepository;

    @Autowired
    public AssociaCartao(SolicitaCartao resultadoCartao, LoggerSolicitaCartao loggerSolicitaCartao,
            PropostaRepository propostaRepository) {
        this.resultadoCartao = resultadoCartao;
        this.loggerSolicitaCartao = loggerSolicitaCartao;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedDelayString = "PT20S")
    public void associa() {
        try {
            Collection<Proposta> propostas = propostaRepository.findByStatus(StatusProposta.ELEGIVEL);
            if (propostas.size() > 0) {
                propostas.stream().forEach(proposta -> {
                    CartaoResposta cartao = resultadoCartao.getCartao(new SendProposta(proposta));
                    proposta.addCartao(cartao.getId());
                    propostaRepository.save(proposta);
                    loggerSolicitaCartao.infoCartao(cartao);
                    System.out.println("Proposta Documento: " + proposta.getCpfORcnpj() + " Cartao gerado!");
                });
            }
        } catch (Exception e) {
            System.out.println("Enviando propostas");
        }
    }

}
