package br.com.acabouMony_usuario.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CadastroUsuarioDTO(
        @NotBlank
        String nome,
        String login,
        String password,
        String cpf,
        String telefone,
        Date dtNasc
) {
}
