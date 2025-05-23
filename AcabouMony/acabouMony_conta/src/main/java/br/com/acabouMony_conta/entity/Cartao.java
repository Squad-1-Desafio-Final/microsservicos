package br.com.acabouMony_conta.entity;

import br.com.acabouMony_conta.tipos.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCartao;

    private String numero;

    private int cvv;

    private int senha;

    private String validade;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    private UUID idConta;

    private Boolean ativo;
}
