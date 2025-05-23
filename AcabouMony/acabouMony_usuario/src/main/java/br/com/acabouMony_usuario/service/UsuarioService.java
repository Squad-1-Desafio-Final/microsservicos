package br.com.acabouMony_usuario.service;

import br.com.acabouMony_usuario.dto.CadastroUsuarioDTO;
import br.com.acabouMony_usuario.dto.ListagemUsuarioDTO;
import br.com.acabouMony_usuario.entity.Usuario;
import br.com.acabouMony_usuario.mapper.UsuarioMapper;
import br.com.acabouMony_usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional
    public ListagemUsuarioDTO saveUsuario(CadastroUsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.email())) {
            throw new RuntimeException("Este e-mail já está cadastrado.");
        }
        var usuario = new Usuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return usuarioMapper.toListagemUsuarioDTO(usuario);
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
        usuarioRepository.atualizarEmail(id, email);
    }

    public void atualizarSenha(UUID id, String senha) {
        usuarioRepository.atualizarSenha(id, senha);
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

