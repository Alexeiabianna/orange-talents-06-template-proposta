package com.alexei.proposta.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.alexei.proposta.controllers.form.BiometriaForm;
import com.alexei.proposta.models.Biometria;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.repository.BiometriaRepository;
import com.alexei.proposta.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    private BiometriaRepository biometriaRepository;
    private PropostaRepository propostaRepository;

    @Autowired
    public BiometriaController(BiometriaRepository biometriaRepository, PropostaRepository propostaRepository) {
        this.biometriaRepository = biometriaRepository;
        this.propostaRepository = propostaRepository;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastra(@PathVariable Long id, @RequestBody @Valid BiometriaForm form, UriComponentsBuilder uriBuilder) {
        if(propostaRepository.existsById(id)) {
            Optional<Proposta> optionalProposta = propostaRepository.findById(id);
            Proposta proposta = optionalProposta.get();
            Biometria biometria = form.toModel(proposta);
            biometriaRepository.save(biometria);    
    
            URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        return ResponseEntity.notFound().build();
    }
}
