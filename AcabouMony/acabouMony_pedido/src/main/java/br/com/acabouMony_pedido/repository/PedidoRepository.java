package br.com.acabouMony_pedido.repository;

import br.com.acabouMony_pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Pedido p SET p.carrinho = false where id = :idPedido")
    int updateCarrinho(UUID idPedido);
}
