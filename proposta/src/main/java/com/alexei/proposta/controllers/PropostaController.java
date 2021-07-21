package com.alexei.proposta.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.alexei.proposta.controllers.client.documento.SendProposta;
import com.alexei.proposta.controllers.client.documento.StatusCliente;
import com.alexei.proposta.controllers.client.documento.StatusDocumento;
import com.alexei.proposta.controllers.client.documento.StatusResposta;
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

import feign.FeignException;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaRepository propostaRepository;
    private LoggerProposta loggerProposta;
    private StatusDocumento statusDocumento;

    @Autowired
    public PropostaController(PropostaRepository propostaRepository, LoggerProposta loggerProposta,
            StatusDocumento statusDocumento) {
        this.propostaRepository = propostaRepository;
        this.loggerProposta = loggerProposta;
        this.statusDocumento = statusDocumento;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriBuilder) {
        Optional<Proposta> optionalProposta = propostaRepository.findBycpfORcnpj(form.getCpfORcnpj());
        if (!optionalProposta.isPresent()) {
            Proposta proposta = form.toModel(StatusCliente.EM_ANALISE);

            try {
                StatusResposta status = statusDocumento.getStatus(new SendProposta(proposta));

                loggerProposta.infoStatusProposta(status);

                Proposta propostaAnalisada = form.toModel(status.getResultadoSolicitacao());
                propostaRepository.save(propostaAnalisada);

                loggerProposta.infoSaveProposta(proposta);

                URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
                return ResponseEntity.created(uri).build();

            } catch (FeignException f) {
                Proposta propostaAnalisada = form.toModel(StatusCliente.COM_RESTRICAO);
                propostaRepository.save(propostaAnalisada);

                loggerProposta.infoSaveProposta(proposta);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }

        }

        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já existente na base de dados");
    }
}
