package com.alexei.proposta.controllers.client.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-cartao-associacao", url = "http://localhost:8888/api/cartoes/")
public interface SolicitaAssociacao {

    @PostMapping("{id}/carteiras")
    AssociaResposta getAssociaCarteira(@PathVariable("id") String idCartao, @RequestBody SendCarteira send);
    
}
