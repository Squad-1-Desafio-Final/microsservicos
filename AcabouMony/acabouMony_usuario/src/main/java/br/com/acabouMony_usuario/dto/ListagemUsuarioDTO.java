package br.com.acabouMony_usuario.dto;

import java.util.Date;

public record ListagemUsuarioDTO(
        String nome,
        String email,
        String telefone,
        Date dtNasc
) {
}

