package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CadastroPedidoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T13:42:40-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PedidoCadastrarMapperStructImpl implements PedidoCadastrarMapperStruct {

    @Override
    public Pedido toEntity(CadastroPedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        return pedido;
    }

    @Override
    public CadastroPedidoDto toPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        UUID usuario = null;
        List<UUID> produtos = null;

        CadastroPedidoDto cadastroPedidoDto = new CadastroPedidoDto( usuario, produtos );

        return cadastroPedidoDto;
    }

    @Override
    public CadastroPedidoDto toDadosPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        UUID usuario = null;
        List<UUID> produtos = null;

        CadastroPedidoDto cadastroPedidoDto = new CadastroPedidoDto( usuario, produtos );

        return cadastroPedidoDto;
    }
}
