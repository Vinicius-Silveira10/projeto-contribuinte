package org.aplicacao.api_real.service;

import lombok.RequiredArgsConstructor;
import org.aplicacao.api_real.model.AuditoriaConsulta;
import org.aplicacao.api_real.model.Contribuinte;
import org.aplicacao.api_real.model.Divida;
import org.aplicacao.api_real.repository.AuditoriaRepository;
import org.aplicacao.api_real.repository.ContribuinteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Adiciona o construtor para os campos 'final'
public class ContribuinteService {

    // Declara as dependências como 'final'
    private final ContribuinteRepository contribuinteRepository;
    private final AuditoriaRepository auditoriaRepository;

    // Pesquisa segura com auditoria
    public Optional<Contribuinte> buscarPorCpf(String cpf, String ip) {
        Optional<Contribuinte> c = contribuinteRepository.findByCpf(cpf);

        AuditoriaConsulta log = new AuditoriaConsulta();
        log.setCpfConsultado(cpf);
        log.setIp(ip);
        log.setTimestamp(LocalDateTime.now());
        auditoriaRepository.save(log);

        return c;
    }

    public void salvarNovoContribuinte(String cpf, Optional<Divida> dadosExternos) {
        if (!contribuinteRepository.existsByCpf(cpf)) {
            dadosExternos.ifPresent(divida -> {
                Contribuinte novoContribuinte = new Contribuinte();
                novoContribuinte.setCpf(cpf);
                novoContribuinte.setNome(divida.getContribuinte());
                novoContribuinte.setDataCadastro(LocalDateTime.now());

                contribuinteRepository.save(novoContribuinte);
                System.out.println("Novo contribuinte salvo no banco de dados com CPF: " + cpf);
            });
        }
    }

    // Método de limpeza agora está no nível correto da classe
    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void limparContribuintesAntigos() {
        LocalDateTime limite = LocalDateTime.now().minusHours(24);
        System.out.println("Executando tarefa de limpeza de contribuintes mais antigos que " + limite);

        contribuinteRepository.deleteByDataCadastroBefore(limite);
    }
}