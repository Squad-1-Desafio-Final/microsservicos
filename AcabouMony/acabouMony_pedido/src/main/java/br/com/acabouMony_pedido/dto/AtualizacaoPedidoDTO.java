package br.com.acabouMony_pedido.dto;

import br.com.acabouMony_pedido.tipos.TipoPagamento;

import java.util.List;
import java.util.UUID;

public record AtualizacaoPedidoDTO(TipoPagamento tipo,
                                   List<UUID> produtos) {
}
