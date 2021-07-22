package com.alexei.proposta.controllers;

import java.util.Optional;

import com.alexei.proposta.controllers.dto.SituacaoPropostaDto;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/situacao")
public class AcompanhamentoController {

    @Autowired
    private PropostaRepository propostaRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getSituacao(@PathVariable Long id) {
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        if(optionalProposta.isPresent()) {
            Proposta proposta = optionalProposta.get();
            return ResponseEntity.ok().body(new SituacaoPropostaDto(proposta));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
