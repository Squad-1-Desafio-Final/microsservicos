package br.com.acabouMony_transacao.dto;

import java.util.UUID;

public record CartaoResumoDto(
        int numero,
        String tipo,
        UUID idConta
) {
}
