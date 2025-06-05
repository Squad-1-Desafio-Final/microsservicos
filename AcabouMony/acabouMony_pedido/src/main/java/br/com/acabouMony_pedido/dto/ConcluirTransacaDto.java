package br.com.acabouMony_pedido.dto;

import java.util.UUID;

public record ConcluirTransacaDto(UUID idUsuario,
                                  UUID idPedido) {
}
