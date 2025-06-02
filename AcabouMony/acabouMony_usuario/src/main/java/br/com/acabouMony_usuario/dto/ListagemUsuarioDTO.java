package br.com.acabouMony_usuario.dto;

import br.com.acabouMony_usuario.entity.Usuario;

import java.util.Date;
import java.util.UUID;

public record ListagemUsuarioDTO(
        UUID id,
        String nome,
        String login,
        String telefone,
        Date dtNasc
) {
    public ListagemUsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(), // ou getLogin() se preferir
                usuario.getTelefone(),
                usuario.getDtNasc()
        );
    }
}
