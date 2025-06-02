package br.com.acabouMony_pedido.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ListagemPedidoDto(
        UUID id,
        UUID idUsuario,
        List<UUID> produtos,
        double precoTotal,
        LocalDateTime date,
        boolean carrinho
){}
