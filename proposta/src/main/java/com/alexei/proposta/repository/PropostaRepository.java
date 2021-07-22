package com.alexei.proposta.repository;

import java.util.Optional;

import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.models.StatusProposta;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    Optional<Proposta> findBycpfORcnpj(String cpfORcnpj);

    Optional<Proposta> findByStatus(StatusProposta status);

    Optional<Proposta> findByEmail(String string);

}
