package br.com.acabouMony_transacao.dto;

import java.util.UUID;

public record CartaoResumoDto(
        String numero,
        String tipo
) {
}
