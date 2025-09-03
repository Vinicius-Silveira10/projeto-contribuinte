package org.aplicacao.api_real.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class Divida {
    private int inscricao;
    private String contribuinte;
    @JsonProperty("tipo_cadastro")
    private String tipoCadastro;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    private String documento;
    private String credito;
    private int parcela;
    private String tributo;
    private int exercicio;
    @JsonProperty("valor_original")
    private String valorOriginal;
    private String status;
    private String referencia;
}