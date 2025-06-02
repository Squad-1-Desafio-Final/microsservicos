package br.com.acabouMony_transacao.dto;

import java.util.UUID;

public record UsuarioResumoDto(
        UUID id,
        String nome,
        String login
) {
}
