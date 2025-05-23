package br.com.acabouMony_conta.dto;

import br.com.acabouMony_conta.tipos.TipoPagamento;

public record ListagemCartaoDTO(String numero,
                                TipoPagamento tipo) {
}
