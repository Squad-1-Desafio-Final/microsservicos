package br.com.acabouMony_usuario.controller;

import br.com.acabouMony_usuario.dto.*;
import br.com.acabouMony_usuario.entity.Usuario;
import br.com.acabouMony_usuario.infra.security.TokenService;
import br.com.acabouMony_usuario.repository.UsuarioRepository;
import br.com.acabouMony_usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid RegisterDTO dto) {
        try {
            // Salva e retorna o usuário criado como DTO
            ListagemUsuarioDTO usuarioSalvo = usuarioService.saveUsuario(dto);
            return ResponseEntity.status(201).body(usuarioSalvo);
        } catch (RuntimeException e) {
            // Retorna mensagem de erro em JSON
            Map<String, String> error = new HashMap<>();
            error.put("error", "Ocorreu um erro ao cadastrar usuário! Já existe um cadastro com esse email!");
            return ResponseEntity.status(409).body(error);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        var listarUsuarios = usuarioService.listarTodos();

        if (listarUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(listarUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarUmUsuario(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(usuarioService.listarUmUsuario(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/nome")
    public ResponseEntity<Usuario> atualizarNome(@PathVariable UUID id, @RequestBody @Valid AtualizarNomeUsuarioDTO nomeDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarNome(id, nomeDTO.nome());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<Usuario> atualizarEmail(@PathVariable UUID id, @RequestBody @Valid AtualizarEmailUsuarioDTO emailDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarEmail(id, emailDTO.email());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Usuario> atualizarSenha(@PathVariable UUID id, @RequestBody @Valid AtualizarSenhaUsuarioDTO senhaDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarSenha(id, senhaDTO.senha());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/telefone")
    public ResponseEntity<Usuario> atualizarTelefone(@PathVariable UUID id, @RequestBody @Valid AtualizarTelefoneUsuarioDTO telefoneDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarTelefone(id, telefoneDTO.telefone());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        try {
            var usuario = usuarioService.login(dto);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }


}