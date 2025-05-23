package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.CartaoResumoDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T13:09:06-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class CartaoResumoMapperImpl implements CartaoResumoMapper {

    @Override
    public Object toEntity(CartaoResumoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Object object = new Object();

        return object;
    }

    @Override
    public CartaoResumoDto toTransacaoDto(Object entity) {
        if ( entity == null ) {
            return null;
        }

        int numero = 0;
        String tipo = null;

        CartaoResumoDto cartaoResumoDto = new CartaoResumoDto( numero, tipo );

        return cartaoResumoDto;
    }
}
