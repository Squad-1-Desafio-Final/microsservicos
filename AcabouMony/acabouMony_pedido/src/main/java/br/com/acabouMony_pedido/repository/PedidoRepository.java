package br.com.acabouMony_pedido.repository;

import br.com.acabouMony_pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {}
