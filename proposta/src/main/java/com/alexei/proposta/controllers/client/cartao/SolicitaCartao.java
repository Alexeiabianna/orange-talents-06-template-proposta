package com.alexei.proposta.controllers.client.cartao;

import com.alexei.proposta.controllers.client.documento.SendProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "api-cartoes", url = "http://localhost:8888/api/cartoes")
public interface SolicitaCartao {

    @RequestMapping(method = RequestMethod.POST, value = "/")
    CartaoResposta getCartao(SendProposta proposta);    
}
