package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CartaoResumoDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T13:20:13-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
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
