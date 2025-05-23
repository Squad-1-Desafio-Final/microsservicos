package br.com.acabouMony_produto.mapper;

import br.com.acabouMony_produto.dto.CadastroProdutoDto;
import br.com.acabouMony_produto.entity.Produto;
import org.mapstruct.Mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapperStruct {

    // @Mapping(source = "nome", target = "nome")
    Produto toEntity(CadastroProdutoDto dto);
    CadastroProdutoDto toProdutoDto(Produto entity);
    CadastroProdutoDto toDadosProdutoDto(Produto entity);

}
