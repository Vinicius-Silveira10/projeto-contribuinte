package org.aplicacao.api_real.service;

import org.aplicacao.api_real.model.AuditoriaConsulta;
import org.aplicacao.api_real.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    // Método para registrar uma consulta
    public void registrarConsulta(String cpf, String ip) {
        AuditoriaConsulta log = new AuditoriaConsulta();
        log.setCpfConsultado(cpf);
        log.setIp(ip);
        log.setTimestamp(LocalDateTime.now()); // registra o momento da consulta
        auditoriaRepository.save(log); // salva no banco
    }

    // Novo método para listar todas as consultas
    public List<AuditoriaConsulta> listarTodasAsConsultas() {
        return auditoriaRepository.findAll();
    }
}