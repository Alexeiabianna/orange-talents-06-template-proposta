package com.alexei.proposta.repository;

import java.util.Optional;

import com.alexei.proposta.models.Proposta;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    Optional<Proposta> findBycpfORcnpj(String cpfORcnpj);

}
