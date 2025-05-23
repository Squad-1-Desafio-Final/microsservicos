package br.com.acabouMony_pedido.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private java.util.UUID id;

    private UUID idUsuario;

    private List<UUID> idProdutos;

    @PositiveOrZero
    private Double precoTotal;

    @FutureOrPresent
    private Date date;

    private Boolean carrinho;

}
