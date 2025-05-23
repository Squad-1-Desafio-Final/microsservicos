package br.com.acabouMony_transacao.dto;

import br.com.acabouMony_transacao.dto.CartaoResumoDto;

import java.util.Date;
import java.util.UUID;

public record ListagemTransacaoDto(
        br.com.acabouMony_transacao.dto.UsuarioResumoDto usuario,
        CartaoResumoDto cartao,
        Date date,
        UUID pedido
) {
}
