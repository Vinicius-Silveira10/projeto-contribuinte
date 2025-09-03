package org.aplicacao.api_real.service;

import org.aplicacao.api_real.model.Usuario;
import org.aplicacao.api_real.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
        @Autowired
        private UsuarioRepository usuarioRepository;

        public Usuario salvarUsuario (Usuario usuario){
            return usuarioRepository.save(usuario); // salva admin no banco
        }

        public List<Usuario> listarUsuarios () {
            return usuarioRepository.findAll(); // lista todos os admins

        }
        public Optional<Usuario> buscarPorEmail(String email) {
            return usuarioRepository.findByEmail(email);
    }
}
