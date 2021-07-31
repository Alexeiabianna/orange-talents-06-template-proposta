package com.alexei.proposta.controllers.client.cartao;

import com.alexei.proposta.controllers.client.documento.SendProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-cartoes", url = "http://localhost:8888/api/cartoes")
public interface SolicitaCartao {

    @PostMapping("/")
    CartaoResposta getCartao(@RequestBody SendProposta proposta);    
}
