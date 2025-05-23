package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.CadastroCartaoDTO;
import br.com.acabouMony_conta.dto.ListagemCartaoDTO;
import br.com.acabouMony_conta.entity.Cartao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    // @Mapping(source = "numero", target = "numero")
    Cartao toEntity(CadastroCartaoDTO dto);

    ListagemCartaoDTO toCartaoDto(Cartao entity);

}
