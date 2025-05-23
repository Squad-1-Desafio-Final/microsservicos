package br.com.acabouMony_transacao.entity;

import br.com.acabouMony_transacao.tipos.TipoPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private java.util.UUID id;

    @FutureOrPresent
    private Date data;

    @Enumerated(EnumType.STRING)
    private br.com.acabouMony_transacao.tipos.TipoPagamento tipo;

    private UUID idCartao;

    private UUID idDestinatario;

    private UUID idPedido;

}
