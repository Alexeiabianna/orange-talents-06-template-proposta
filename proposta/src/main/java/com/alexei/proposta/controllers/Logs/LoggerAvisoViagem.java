package com.alexei.proposta.controllers.Logs;

import com.alexei.proposta.controllers.AvisoViagemController;
import com.alexei.proposta.controllers.client.aviso.AvisoResposta;
import com.alexei.proposta.models.AvisoViagem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerAvisoViagem {
    private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

    public void infoSaveAviso(AvisoViagem aviso) {
        logger.info("Aviso de viagem criado com sucesso para o cartão {} Cliente {}", aviso.getIdCartao(), aviso.getProposta().getEmail());
    }

    public void infoRespostaAPI(AvisoResposta avisoResposta) {
        logger.info("Retorno Sistema do Banco: {}", avisoResposta.getResultado());
    }
    
}
