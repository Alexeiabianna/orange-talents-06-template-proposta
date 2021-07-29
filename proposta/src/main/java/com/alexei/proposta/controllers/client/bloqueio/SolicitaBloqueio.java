package com.alexei.proposta.controllers.client.bloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-cartoes-bloqueio", url = "http://localhost:8888/api/cartoes/")
public interface SolicitaBloqueio {
    
    @PostMapping("{id}/bloqueios")
    BloqueioResposta getBloqueio(@PathVariable("id") String idCartao, @RequestBody SendBloqueio send); 
}
