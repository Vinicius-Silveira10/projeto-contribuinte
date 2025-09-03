package org.aplicacao.api_real.repository;

import org.aplicacao.api_real.model.AuditoriaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<AuditoriaConsulta, Long> {
    // Aqui você pode adicionar métodos de consulta de auditoria se precisar
}

