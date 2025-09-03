package org.aplicacao.api_real.repository;

import org.aplicacao.api_real.model.Contribuinte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ContribuinteRepository extends JpaRepository<Contribuinte, Long> {
    Optional<Contribuinte> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    void deleteByDataCadastroBefore(LocalDateTime dataLimite);

}