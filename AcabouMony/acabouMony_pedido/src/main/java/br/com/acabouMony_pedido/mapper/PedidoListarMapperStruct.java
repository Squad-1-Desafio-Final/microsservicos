package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CadastroPedidoDto;
import br.com.acabouMony_pedido.dto.ListagemPedidoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoListarMapperStruct {


//  @Mapping(source = "carrinho", target = "carrinho")
    Pedido toEntity(ListagemPedidoDto dto);
    ListagemPedidoDto toPedidoDto(Pedido entity);
    ListagemPedidoDto toDadosPedidoDto(Pedido entity);

}
