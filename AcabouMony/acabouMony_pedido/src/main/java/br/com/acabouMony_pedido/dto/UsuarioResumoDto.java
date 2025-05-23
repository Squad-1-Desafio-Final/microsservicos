package br.com.acabouMony_pedido.dto;

import java.util.UUID;

public record UsuarioResumoDto(
        UUID id,
        String nome,
        String email
) {
}
