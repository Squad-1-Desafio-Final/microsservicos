package br.com.acabouMony_conta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CadastroContaDTO(LocalDate dataVencimento,
                               @NotNull double limite,
                               @NotNull int agencia,
                               @NotNull int numero,
                               @NotNull int banco,
                                UUID idUsuario) {
}
