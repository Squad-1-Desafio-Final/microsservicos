package br.com.acabouMony_conta.dto;

import java.util.UUID;

public record CadastroCartaoDTO(Integer senha,
                                String tipoPagamento,
                                UUID idConta) {
}
