package org.aplicacao.api_real.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.aplicacao.api_real.model.ConsultaDTO;
import org.aplicacao.api_real.model.Contribuinte;
import org.aplicacao.api_real.model.Divida;
import org.aplicacao.api_real.service.ContribuinteService;
import org.aplicacao.api_real.util.CpfValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ContribuinteController {

    private final ContribuinteService contribuinteService;
    private final RestTemplate restTemplate;

    @Autowired
    public ContribuinteController(ContribuinteService contribuinteService, RestTemplate restTemplate) {
        this.contribuinteService = contribuinteService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/cpf")
    public ResponseEntity<?> consultar(@RequestParam String cpf, HttpServletRequest request) {

        if (!CpfValidator.isValid(cpf)) {
            return ResponseEntity.badRequest().body("CPF/CNPJ inválido.");
        }

        String ip = request.getRemoteAddr();

        // 1. Busca no banco de dados local E registra na auditoria
        Optional<Contribuinte> contribuinteLocal = contribuinteService.buscarPorCpf(cpf, ip);

        // 2. Busca na API externa
        //String url = "coloque aqui o seu endpoint e retire o comentário";
        ResponseEntity<Map<String, List<Divida>>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        List<Divida> listaDividas = responseEntity.getBody().get("data");

        Optional<Divida> dividaDoCpf = Optional.ofNullable(listaDividas)
                .flatMap(list -> list.stream()
                        .filter(d -> cpf.equals(d.getCpfCnpj()))
                        .findFirst());

        // 3. Lógica para salvar o novo contribuinte
        // Se o contribuinte não foi encontrado localmente, tentamos salvá-lo com os dados da API externa
        if (contribuinteLocal.isEmpty()) {
            contribuinteService.salvarNovoContribuinte(cpf, dividaDoCpf);
        }

        // 4. Monta a resposta para o frontend
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setContribuinte(contribuinteLocal.orElse(null));
        consultaDTO.setDadosExternos(dividaDoCpf.orElse(null));

        return ResponseEntity.ok(consultaDTO);
    }
}