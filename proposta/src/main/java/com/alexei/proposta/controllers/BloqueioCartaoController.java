package com.alexei.proposta.controllers;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alexei.proposta.models.BloqueioCartao;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.repository.BloqueioCartaoRepository;
import com.alexei.proposta.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioCartaoController {

    private PropostaRepository propostaRepository;
    private BloqueioCartaoRepository bloqueioCartaoRepository;

    @Autowired
    public BloqueioCartaoController(PropostaRepository propostaRepository,
            BloqueioCartaoRepository bloqueioCartaoRepository) {
        this.propostaRepository = propostaRepository;
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
    }

    @PostMapping("/{id}")
    private ResponseEntity<?> bloqueia(@PathVariable Long id, @RequestHeader("User-Agent") @Valid String userAgent, 
        @Valid HttpServletRequest ipClientReq, UriComponentsBuilder uriBuilder) {

        if(!propostaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        String ipClient = GetIPClientHeader.getIpClientRequest(ipClientReq);
        BloqueioForm form = new BloqueioForm(userAgent, ipClient);
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        Proposta proposta = optionalProposta.get(); 
        BloqueioCartao bloqueio = form.toModel(proposta);

        bloqueioCartaoRepository.save(bloqueio);

        URI location = uriBuilder.path("/bloqueio/{id}").buildAndExpand(bloqueio.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
