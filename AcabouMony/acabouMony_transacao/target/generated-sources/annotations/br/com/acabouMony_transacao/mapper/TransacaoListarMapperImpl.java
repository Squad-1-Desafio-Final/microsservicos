package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.CartaoResumoDto;
import br.com.acabouMony_transacao.dto.ListagemTransacaoDto;
import br.com.acabouMony_transacao.dto.UsuarioResumoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T09:55:03-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class TransacaoListarMapperImpl implements TransacaoListarMapper {

    @Override
    public Transacao toEntity(ListagemTransacaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        return transacao;
    }

    @Override
    public ListagemTransacaoDto toTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioResumoDto usuario = null;
        CartaoResumoDto cartao = null;
        Date date = null;
        UUID pedido = null;

        ListagemTransacaoDto listagemTransacaoDto = new ListagemTransacaoDto( usuario, cartao, date, pedido );

        return listagemTransacaoDto;
    }
}
