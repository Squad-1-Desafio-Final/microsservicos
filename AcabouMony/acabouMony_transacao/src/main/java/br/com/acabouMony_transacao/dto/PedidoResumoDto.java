package br.com.acabouMony_transacao.dto;

import br.com.acabouMony_transacao.tipos.TipoPagamento;

import java.util.UUID;

public record PedidoResumoDto (
        UUID id,
        UUID idUsuario,
        boolean carrinho,
        Double precoTotal,
        TipoPagamento tipo
){}
