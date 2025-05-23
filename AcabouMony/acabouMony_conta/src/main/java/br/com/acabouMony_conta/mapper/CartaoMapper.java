package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.CadastroCartaoDTO;
import br.com.acabouMony_conta.dto.ListagemCartaoDTO;
import br.com.acabouMony_conta.entity.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    @Mapping(source = "senha", target = "senha")
    Cartao toEntity(CadastroCartaoDTO dto);

    ListagemCartaoDTO toCartaoDto(Cartao entity);

}
