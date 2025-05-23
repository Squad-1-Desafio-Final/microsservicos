package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.UsuarioResumoDto;
import org.mapstruct.Mapping;

public interface UsuarioResumoMapper {

//    @Mapping(source = "nome", target = "nome")
    Object toEntity(UsuarioResumoDto dto);
    UsuarioResumoDto toPedidoDto(Object entity);
    UsuarioResumoDto toDadosPedidoDto(Object entity);


}
