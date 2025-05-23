package br.com.acabouMony_transacao.mapper;

import br.com.acabouMony_transacao.dto.CadastroTransacaoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import br.com.acabouMony_transacao.entity.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransacaoCadastroMapper {
    //@Mapping(source = "usuario", target = "usuario")
    br.com.acabouMony_transacao.entity.Transacao toEntity(CadastroTransacaoDto dto);
    CadastroTransacaoDto toTransacaoDto(Transacao entity);
    CadastroTransacaoDto toDadosTransacaoDto(Transacao entity);

}
