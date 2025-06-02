package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CadastroPedidoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import br.com.acabouMony_pedido.entity.Produto;
import br.com.acabouMony_pedido.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CadastroPedidoMapper {

    private final ProdutoRepository produtoRepository;

    public CadastroPedidoMapper(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Mapper do DTO -> Entidade
    public Pedido toEntity(CadastroPedidoDto dto) {
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(dto.usuario());
        pedido.setTipo(dto.tipo());

        // Busca os produtos pelo UUID
        List<Produto> produtos = dto.produtos().stream()
                .map(produtoId -> produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produtoId)))
                .toList();

        pedido.setProdutos(produtos);

        // Aqui o preçoTotal, date e carrinho seriam preenchidos no Service
        return pedido;
    }

    // Mapper da Entidade -> DTO
    public CadastroPedidoDto toDto(Pedido pedido) {
        List<UUID> produtosIds = pedido.getProdutos().stream()
                .map(Produto::getIdProduto)
                .toList();

        return new CadastroPedidoDto(
                pedido.getIdUsuario(),
                pedido.getTipo(),
                produtosIds
        );
    }
}
