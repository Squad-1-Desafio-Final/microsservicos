package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.ListagemTransacaoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransacaoListarMapper {
    //@Mapping(source = "usuario", target = "usuario")
    Transacao toEntity(ListagemTransacaoDto dto);
    ListagemTransacaoDto toTransacaoDto(Transacao entity);

}
