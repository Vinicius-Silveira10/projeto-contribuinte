package org.aplicacao.api_real.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auditoria_consulta")
public class AuditoriaConsulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfConsultado; // CPF do cidadão que fez a consulta

    private String ip; // IP da máquina que fez a consulta

    private LocalDateTime timestamp; // horário da consulta

}
