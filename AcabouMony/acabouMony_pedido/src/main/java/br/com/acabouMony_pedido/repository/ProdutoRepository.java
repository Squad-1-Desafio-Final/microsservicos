package br.com.acabouMony_pedido.repository;

import br.com.acabouMony_pedido.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query(
            value = "SELECT * FROM produto p " +
                    "JOIN pedido_produto pp ON p.id = pp.produto_id " +
                    "WHERE pp.pedido_id = :pedidoId",
            nativeQuery = true
    )
    List<Produto> findProdutosByPedidoId(@Param("pedidoId") UUID pedidoId);

    Optional<Produto> findByIdProduto(UUID idProduto);
}

