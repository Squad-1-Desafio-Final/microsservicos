package br.com.acabouMony_pedido.dto;

import java.util.List;
import java.util.UUID;

public record CadastroPedidoDto(
        UUID usuario,
        List<UUID> produtos
) {
}
