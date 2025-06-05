package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.ListagemCartaoDTO;
import br.com.acabouMony_conta.entity.Cartao;
import br.com.acabouMony_conta.entity.Conta;
import br.com.acabouMony_conta.tipos.TipoPagamento;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {
    // Entidade -> DTO
    public ListagemCartaoDTO toDto(Cartao cartao) {
        return new ListagemCartaoDTO(
                cartao.getNumero(),
                cartao.getTipo()
        );
    }

    // DTO -> Entidade
    public Cartao toEntity(ListagemCartaoDTO dto) {
        Cartao cartao = new Cartao();
        cartao.setNumero(dto.numero());
        cartao.setTipo(dto.tipo());
        // Os demais campos (cvv, senha, validade, idConta, ativo)
        // devem ser setados em outro contexto (ex: Service ou Controller).
        return cartao;
    }
}