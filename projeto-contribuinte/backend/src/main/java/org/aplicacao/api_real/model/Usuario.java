package org.aplicacao.api_real.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID único do administrador

    private String nome;

    @Column(unique = true)
    private String email; // único, para identificar admin

    private String senha; // pode ser usada futuramente para autenticação
}
