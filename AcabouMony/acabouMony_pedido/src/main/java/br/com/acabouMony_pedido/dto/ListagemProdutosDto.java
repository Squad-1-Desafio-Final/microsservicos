package br.com.acabouMony_pedido.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ListagemProdutosDto(
        UUID id,
        BigDecimal preco
) {
}
