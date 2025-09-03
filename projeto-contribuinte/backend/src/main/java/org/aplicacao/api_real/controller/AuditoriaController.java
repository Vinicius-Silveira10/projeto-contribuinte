package org.aplicacao.api_real.controller;


import org.aplicacao.api_real.model.AuditoriaConsulta;
import org.aplicacao.api_real.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<List<AuditoriaConsulta>> listarAuditoria() {
        List<AuditoriaConsulta> auditoria = auditoriaService.listarTodasAsConsultas();
        return ResponseEntity.ok(auditoria);
    }
}