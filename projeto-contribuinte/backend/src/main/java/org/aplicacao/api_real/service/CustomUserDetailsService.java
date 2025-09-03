package org.aplicacao.api_real.service;

import lombok.RequiredArgsConstructor; // 1. Importe a anotação
import org.aplicacao.api_real.model.Usuario;
import org.aplicacao.api_real.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // 3.  declara o campo como 'final'
    private final UsuarioRepository usuarioRepository;
    // O Lombok gera o construtor para você em tempo de compilação!

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}