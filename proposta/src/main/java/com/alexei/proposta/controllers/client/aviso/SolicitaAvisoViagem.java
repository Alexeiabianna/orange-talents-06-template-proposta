package com.alexei.proposta.controllers.client.aviso;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-cartoes-aviso", url = "http://localhost:8888/api/cartoes/")
public interface SolicitaAvisoViagem {
   
    @PostMapping("{id}/avisos")
    AvisoResposta getAviso(@PathVariable("id") String idCartao, @RequestBody SendAviso send);
}
