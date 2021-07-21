package com.alexei.proposta.controllers.Logs;

import com.alexei.proposta.controllers.PropostaController;
import com.alexei.proposta.controllers.client.documento.StatusResposta;
import com.alexei.proposta.models.Proposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerProposta {
    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    public void infoSaveProposta(Proposta proposta) {
        logger.info("Proposta documento={} salário={} criada com sucesso!", proposta.getCpfORcnpj(), proposta.getSalario());
    }

    public void infoStatusProposta(StatusResposta status) {                        
        logger.info("Status da proposta com documento {} é {}", status.getDocumento(), status.getResultadoSolicitacao());
    }
    
}
