package org.aplicacao.api_real.controller;

import org.aplicacao.api_real.model.Usuario;
import org.aplicacao.api_real.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios") // endpoint principal para admins
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Cria um novo usuário admin
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario u = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(u); // retorna o admin criado
    }

    // Lista todos os usuários admin
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios); // retorna lista de admins
    }

    // Consulta um admin específico pelo email (opcional)
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}