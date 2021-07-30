package com.alexei.proposta.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.alexei.proposta.controllers.client.carteira.AssociaResposta;
import com.alexei.proposta.controllers.client.carteira.SendCarteira;
import com.alexei.proposta.controllers.client.carteira.SolicitaAssociacao;
import com.alexei.proposta.controllers.form.CarteiraForm;
import com.alexei.proposta.models.Carteira;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.repository.CarteiraRepository;
import com.alexei.proposta.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException;

@RestController
@RequestMapping("/carteira/paypal")
public class AssociaPaypalController {

    private PropostaRepository propostaRepository;
    private CarteiraRepository carteiraRepository;
    private SolicitaAssociacao solicitaAssociacao;

    @Autowired
    public AssociaPaypalController(PropostaRepository propostaRepository, CarteiraRepository carteiraRepository,
            SolicitaAssociacao solicitaAssociacao) {
        this.propostaRepository = propostaRepository;
        this.carteiraRepository = carteiraRepository;
        this.solicitaAssociacao = solicitaAssociacao;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> associa(@PathVariable Long id, @Valid @RequestBody CarteiraForm form) {

        if(!propostaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        Proposta proposta = optionalProposta.get();
        Carteira carteira = form.toModel(proposta);

        try {
            String idCartao = carteira.getIdCartao();
            String email = carteira.getEmail();
            String nomeCarteira = carteira.getCarteira();
            SendCarteira send = new SendCarteira(email, nomeCarteira);
            AssociaResposta associaResposta = solicitaAssociacao.getAssociaCarteira(idCartao, send);

            if(associaResposta.isValid() && carteira.isValidId()) {
                carteiraRepository.save(carteira);

                return ResponseEntity.ok().build();
            }

            
        } catch (FeignException f) {
            return ResponseEntity.unprocessableEntity().build();
        }       
        
        return ResponseEntity.unprocessableEntity().build();
    }
}
