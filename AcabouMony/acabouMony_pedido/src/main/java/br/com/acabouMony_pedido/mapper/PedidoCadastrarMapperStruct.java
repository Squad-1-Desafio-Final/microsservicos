package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CadastroPedidoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoCadastrarMapperStruct {

// @Mapping(source = "produtos", target = "Produtos")
    Pedido toEntity(CadastroPedidoDto dto);
    CadastroPedidoDto toPedidoDto(Pedido entity);
    CadastroPedidoDto toDadosPedidoDto(Pedido entity);

}
