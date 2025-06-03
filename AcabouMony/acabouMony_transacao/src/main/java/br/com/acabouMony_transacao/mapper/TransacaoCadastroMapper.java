package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.CadastroTransacaoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import br.com.acabouMony_transacao.entity.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransacaoCadastroMapper {

    public Transacao toEntity(CadastroTransacaoDto dto) {
        if (dto == null) {
            return null;
        }

        Transacao transacao = new Transacao();
        transacao.setTipo(dto.tipo());
        transacao.setIdCartao(dto.cartao());
        transacao.setIdDestinatario(dto.usuario());
        transacao.setIdPedido(dto.pedido());


        transacao.setData(new Date());

        return transacao;
    }

    public CadastroTransacaoDto toTransacaoDto(Transacao entity) {
        if (entity == null) {
            return null;
        }

        return new CadastroTransacaoDto(
                entity.getTipo(),
                entity.getIdCartao(),
                entity.getIdDestinatario(),
                entity.getIdPedido()
        );
    }
}
