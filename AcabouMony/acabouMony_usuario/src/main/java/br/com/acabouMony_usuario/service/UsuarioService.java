package br.com.acabouMony_usuario.service;

import br.com.acabouMony_usuario.dto.*;
import br.com.acabouMony_usuario.entity.Usuario;
import br.com.acabouMony_usuario.infra.security.TokenService;
import br.com.acabouMony_usuario.mapper.UsuarioMapper;
import br.com.acabouMony_usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public UsuarioService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Transactional
//    public ListagemUsuarioDTO saveUsuario(RegisterDTO dto) {
//        if (usuarioRepository.existsByLogin(dto.login())) {
//            throw new RuntimeException("Este e-mail já está cadastrado.");
//        }
//        var usuario = new Usuario(dto);
//        Usuario usuarioSalvo = usuarioRepository.save(usuario);
//
//        kafkaTemplate.send("usuario-criado","Usuário criado com sucesso");
//
//        return usuarioMapper.toListagemUsuarioDTO(usuarioSalvo);
//    }

    @Transactional
    public ListagemUsuarioDTO saveUsuario(RegisterDTO dto) {
        validarLoginExistente(dto.login());

        Usuario usuarioSalvo = usuarioRepository.save(new Usuario(dto));

        kafkaTemplate.send("usuario-criado", "Usuário criado com sucesso");

        return usuarioMapper.toListagemUsuarioDTO(usuarioSalvo);
    }

    private void validarLoginExistente(String login) {
        if (usuarioRepository.existsByLogin(login)) {
            throw new RuntimeException("Este e-mail já está cadastrado.");
        }
    }

    public String login(AuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return token;
    }

    public void deleteUsuario(UUID id) {
        var usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            throw new RuntimeException("Não há usuário cadastrado para o id " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public void atualizarNome(UUID id, String nome) {
        usuarioRepository.atualizarNome(id, nome);
    }

    public void atualizarEmail(UUID id, String email) {
        usuarioRepository.atualizarLogin(id, email);
    }

    public void atualizarSenha(UUID id, String senha) {
        usuarioRepository.atualizarpassword(id, senha);
    }

    public void atualizarTelefone(UUID id, String telefone) {
        usuarioRepository.atualizarTelefone(id, telefone);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario listarUmUsuario(UUID id) {
        var endereco = usuarioRepository.findById(id);

        if (endereco.isEmpty()) {
            throw new RuntimeException("Usuário com ID não encontrado!");
        }

        return endereco.get();
    }
}

