package com.alexei.proposta.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.alexei.proposta.controllers.Logs.LoggerAvisoViagem;
import com.alexei.proposta.controllers.client.aviso.AvisoResposta;
import com.alexei.proposta.controllers.client.aviso.SendAviso;
import com.alexei.proposta.controllers.client.aviso.SolicitaAvisoViagem;
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

import feign.FeignException;

@RestController
@RequestMapping("/viagem")
public class AvisoViagemController {

    private PropostaRepository propostaRepository;
    private AvisoViagemRepository avisoViagemRepository;
    private SolicitaAvisoViagem solicitaAvisoViagem;
    private LoggerAvisoViagem loggerAvisoViagem;

    @Autowired
    public AvisoViagemController(PropostaRepository propostaRepository, AvisoViagemRepository avisoViagemRepository,
            SolicitaAvisoViagem solicitaAvisoViagem, LoggerAvisoViagem loggerAvisoViagem) {
        this.propostaRepository = propostaRepository;
        this.avisoViagemRepository = avisoViagemRepository;
        this.solicitaAvisoViagem = solicitaAvisoViagem;
        this.loggerAvisoViagem = loggerAvisoViagem;
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

        try {
            String destino = form.getDestino();
            String dataTermino = form.getDataTerminoToString();
            String idCartao = avisoViagem.getIdCartao();
            SendAviso send = new SendAviso(destino, dataTermino);
            AvisoResposta avisoResposta = solicitaAvisoViagem.getAviso(idCartao, send);
            loggerAvisoViagem.infoRespostaAPI(avisoResposta);

            if (avisoViagem.isValidIdCartao() && avisoResposta.isValid()) {
                avisoViagemRepository.save(avisoViagem);
                loggerAvisoViagem.infoSaveAviso(avisoViagem);
                return ResponseEntity.ok().body(form);
            }

        } catch (FeignException f) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.unprocessableEntity().build();
    }
}
