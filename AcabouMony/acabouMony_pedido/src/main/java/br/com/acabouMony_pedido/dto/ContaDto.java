package br.com.acabouMony_pedido.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record ContaDto(UUID idConta,
                       LocalDate dataVencimento,
                       double saldo,
                       double limite,
                       double credito,
                       int agencia,
                       int numero,
                       int banco,
                       Date dataCriacao,
                       boolean ativo,
                       UUID idUsuario) {
}
