package br.com.acabouMony_transacao.dto;

import java.util.Date;
import java.util.UUID;

public record TransacaoResumoDto(Date data, UUID idCartao, UUID idDestinatario, UUID idPedido) {
}
