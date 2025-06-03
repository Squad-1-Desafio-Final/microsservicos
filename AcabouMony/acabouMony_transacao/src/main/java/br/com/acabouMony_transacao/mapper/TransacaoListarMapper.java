package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.ListagemTransacaoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
public class TransacaoListarMapper {

    public Transacao toEntity(ListagemTransacaoDto dto) {
        if (dto == null) {
            return null;
        }

        Transacao transacao = new Transacao();

        transacao.setIdCartao(dto.cartao());
        transacao.setIdDestinatario(dto.usuario());
        transacao.setData(dto.date());
        transacao.setIdPedido(dto.pedido());

        return transacao;
    }

    public ListagemTransacaoDto toTransacaoDto(Transacao entity) {
        if (entity == null) {
            return null;
        }

        return new ListagemTransacaoDto(
                entity.getIdDestinatario(),
                entity.getIdCartao(),
                entity.getData(),
                entity.getIdPedido()
        );
    }
}
