package org.aplicacao.api_real.model;

import jakarta.persistence.*;
import org.aplicacao.api_real.util.CpfCriptoConverter;
import org.springframework.data.annotation.CreatedDate;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contribuintes")
public class Contribuinte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    @Convert(converter = CpfCriptoConverter.class)
    private String cpf; // será criptografado no banco

    private String endereco;

    private String telefone;

    private String email;

    @CreatedDate
    private LocalDateTime dataCadastro; // registra quando o dado foi inserido
}