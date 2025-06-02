package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.ListagemPedidoDto;
import br.com.acabouMony_pedido.dto.UsuarioResumoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T15:33:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PedidoListarMapperStructImpl implements PedidoListarMapperStruct {

    @Override
    public Pedido toEntity(ListagemPedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        return pedido;
    }

    @Override
    public ListagemPedidoDto toPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        UsuarioResumoDto usuario = null;
        List<UUID> produtos = null;
        double precoTotal = 0.0d;
        LocalDateTime date = null;
        boolean carrinho = false;

        ListagemPedidoDto listagemPedidoDto = new ListagemPedidoDto( id, usuario, produtos, precoTotal, date, carrinho );

        return listagemPedidoDto;
    }

    @Override
    public ListagemPedidoDto toDadosPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        UsuarioResumoDto usuario = null;
        List<UUID> produtos = null;
        double precoTotal = 0.0d;
        LocalDateTime date = null;
        boolean carrinho = false;

        ListagemPedidoDto listagemPedidoDto = new ListagemPedidoDto( id, usuario, produtos, precoTotal, date, carrinho );

        return listagemPedidoDto;
    }
}
