package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.UsuarioResumoDto;
import org.mapstruct.Mapping;

public interface UsuarioResumoMapper {

//    @Mapping(source = "nome", target = "nome")
    Object toEntity(UsuarioResumoDto dto);
    UsuarioResumoDto toPedidoDto(Object entity);
    UsuarioResumoDto toDadosPedidoDto(Object entity);


}
