package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CartaoResumoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartaoResumoMapper {

//    @Mapping(source = "numero", target = "numero")
    Object toEntity(CartaoResumoDto dto);
    CartaoResumoDto toTransacaoDto(Object entity);
}