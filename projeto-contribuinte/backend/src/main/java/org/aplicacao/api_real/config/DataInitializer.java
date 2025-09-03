package org.aplicacao.api_real.config;

import org.aplicacao.api_real.model.Usuario;
import org.aplicacao.api_real.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            // Verifica se o usuário admin já existe
            if (usuarioRepository.findByEmail("admin@example.com").isEmpty()) {
                System.out.println("Criando usuário admin inicial...");

                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@example.com");
                // Senha exemplo
                admin.setSenha(passwordEncoder.encode("Roger123"));

                usuarioRepository.save(admin);
                System.out.println("Usuário admin criado com sucesso.");
            }
        };
    }
}