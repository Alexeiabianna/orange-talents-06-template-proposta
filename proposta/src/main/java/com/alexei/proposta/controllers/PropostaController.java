package com.alexei.proposta.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriBuilder) {
        Optional<Proposta> optionalProposta = propostaRepository.findBycpfORcnpj(form.getCpfORcnpj());
        if (!optionalProposta.isPresent()) {
            Proposta proposta = form.toModel();

            propostaRepository.save(proposta);

            URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já existente na base de dados");
    }
}
