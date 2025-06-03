package br.com.acabouMony_transacao.dto;

import br.com.acabouMony_transacao.dto.CartaoResumoDto;

import java.util.Date;
import java.util.UUID;

public record ListagemTransacaoDto(
        UUID usuario,
        UUID cartao,
        Date date,
        UUID pedido
) {
}
