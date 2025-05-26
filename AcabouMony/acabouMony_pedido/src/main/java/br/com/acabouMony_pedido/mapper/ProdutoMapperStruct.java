package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CadastroProdutoDto;
import br.com.acabouMony_pedido.entity.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapperStruct {

    // @Mapping(source = "nome", target = "nome")
    Produto toEntity(CadastroProdutoDto dto);
    CadastroProdutoDto toProdutoDto(Produto entity);
    CadastroProdutoDto toDadosProdutoDto(Produto entity);

}
