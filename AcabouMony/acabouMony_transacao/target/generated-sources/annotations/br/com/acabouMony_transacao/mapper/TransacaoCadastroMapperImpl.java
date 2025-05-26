package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.CadastroTransacaoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import br.com.acabouMony_transacao.tipos.TipoPagamento;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T09:55:03-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class TransacaoCadastroMapperImpl implements TransacaoCadastroMapper {

    @Override
    public Transacao toEntity(CadastroTransacaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        return transacao;
    }

    @Override
    public CadastroTransacaoDto toTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        TipoPagamento tipo = null;
        UUID cartao = null;
        UUID usuario = null;
        UUID pedido = null;

        CadastroTransacaoDto cadastroTransacaoDto = new CadastroTransacaoDto( tipo, cartao, usuario, pedido );

        return cadastroTransacaoDto;
    }

    @Override
    public CadastroTransacaoDto toDadosTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        TipoPagamento tipo = null;
        UUID cartao = null;
        UUID usuario = null;
        UUID pedido = null;

        CadastroTransacaoDto cadastroTransacaoDto = new CadastroTransacaoDto( tipo, cartao, usuario, pedido );

        return cadastroTransacaoDto;
    }
}
