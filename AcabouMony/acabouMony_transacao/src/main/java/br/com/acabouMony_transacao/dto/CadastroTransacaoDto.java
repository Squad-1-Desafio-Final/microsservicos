package br.com.acabouMony_transacao.dto;

import br.com.acabouMony_transacao.tipos.TipoPagamento;

import java.util.UUID;

public record CadastroTransacaoDto(
        TipoPagamento tipo,
        UUID cartao,
        UUID destinatario,
        UUID pedido
) { }
