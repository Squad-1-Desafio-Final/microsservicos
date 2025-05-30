package br.com.acabouMony_pedido.dto;

import java.util.UUID;

public record UsuarioDto(UUID id,
                         String nome,
                         String email,
                         double saldo,
                         double limite,
                         double credito) {
}
