package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.ListagemPedidoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import br.com.acabouMony_pedido.entity.Produto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Component
public class ListagemPedidoMapper {

    // Remove a dependência do UsuarioService e recebe UsuarioResumoDto de fora

    public ListagemPedidoDto toDto(Pedido pedido) {

        List<UUID> produtosIds = pedido.getProdutos().stream()
                .map(Produto::getIdProduto)
                .toList();

        LocalDateTime localDateTime = pedido.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return new ListagemPedidoDto(
                pedido.getId(),
                pedido.getIdUsuario(),
                produtosIds,
                pedido.getPrecoTotal(),
                localDateTime,
                pedido.getCarrinho()
        );
    }
}
