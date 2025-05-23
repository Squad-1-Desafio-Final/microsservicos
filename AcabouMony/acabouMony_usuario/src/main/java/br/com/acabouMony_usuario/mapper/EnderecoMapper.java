package br.com.acabouMony_usuario.mapper;

import br.com.acabouMony_usuario.dto.CadastroEnderecoDTO;
import br.com.acabouMony_usuario.dto.ListagemEnderecoDTO;
import br.com.acabouMony_usuario.entity.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    //    @Mapping(source = "numero", target = "numero")
    Endereco toEntity(CadastroEnderecoDTO dto);

    ListagemEnderecoDTO toListagemEnderecoDTO(Endereco endereco);

}
