package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.CadastroContaDTO;
import br.com.acabouMony_conta.dto.ListagemContaDTO;
import br.com.acabouMony_conta.entity.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContaMapper {

//    @Mapping(source = "numero", target = "numero")
    Conta toEntity(CadastroContaDTO dto);

    ListagemContaDTO toListagemContaDTO(Conta conta);
}
