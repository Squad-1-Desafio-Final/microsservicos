package br.com.acabouMony_usuario.mapper;

import br.com.acabouMony_usuario.dto.CadastroUsuarioDTO;
import br.com.acabouMony_usuario.dto.ListagemUsuarioDTO;
import br.com.acabouMony_usuario.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
        @Mapping(source = "nome", target = "nome")
    Usuario toEntity(CadastroUsuarioDTO dto);
       @Mapping(source = "login", target = "login")
    ListagemUsuarioDTO toListagemUsuarioDTO(Usuario usuario);
}