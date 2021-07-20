package com.alexei.proposta.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "analise-financeira", url = "http://localhost:9999/api/solicitacao")
public interface StatusDocumento {
    @RequestMapping(method = RequestMethod.POST, value = "/")
    StatusResposta getStatus(PropostaDto proposta);    
}
