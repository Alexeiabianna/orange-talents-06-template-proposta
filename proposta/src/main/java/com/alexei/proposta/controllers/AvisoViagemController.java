package com.alexei.proposta.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.alexei.proposta.controllers.form.AvisoViagemForm;
import com.alexei.proposta.models.AvisoViagem;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.repository.AvisoViagemRepository;
import com.alexei.proposta.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viagem")
public class AvisoViagemController {

    private PropostaRepository propostaRepository;
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    public AvisoViagemController(PropostaRepository propostaRepository, AvisoViagemRepository avisoViagemRepository) {
        this.propostaRepository = propostaRepository;
        this.avisoViagemRepository = avisoViagemRepository;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> criaAviso(@PathVariable Long id, @Valid @RequestBody AvisoViagemForm form,
            @RequestHeader("User-Agent") String userAgent, HttpServletRequest ipClient) {

        if (!propostaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        Proposta proposta = optionalProposta.get();

        String ipCliente = GetIPClientHeader.getIpClientRequest(ipClient);
        AvisoViagem avisoViagem = form.toModel(userAgent, ipCliente, proposta);

        avisoViagemRepository.save(avisoViagem);

        

        return ResponseEntity.ok().body(form);
    }
}
