package com.alexei.proposta.controllers.Logs;

import com.alexei.proposta.controllers.client.cartao.CartaoResposta;
import com.alexei.proposta.controllers.client.cartao.SolicitaCartao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerSolicitaCartao {
    private final Logger logger = LoggerFactory.getLogger(SolicitaCartao.class);

    public void infoCartao(CartaoResposta cartao) {
        logger.info("Cartao numero={} emissao={} titular={} criado com sucesso!", cartao.getId(), cartao.getEmitidoEm(),
                cartao.getTitular());
    }
}
