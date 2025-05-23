package br.com.acabouMony_usuario.mapper;

import br.com.acabouMony_usuario.dto.UsuarioResumoDto;
import br.com.acabouMony_usuario.entity.Usuario;
import org.mapstruct.Mapping;

public interface UsuarioResumoMapper {

        @Mapping(source = "nome", target = "nome")
    Usuario toEntity(UsuarioResumoDto dto);
    UsuarioResumoDto toPedidoDto(Usuario entity);
    UsuarioResumoDto toDadosPedidoDto(Usuario entity);


}
