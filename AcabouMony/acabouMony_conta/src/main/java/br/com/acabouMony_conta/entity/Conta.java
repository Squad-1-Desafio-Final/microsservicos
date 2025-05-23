package br.com.acabouMony_conta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idConta;

    private LocalDate dataVencimento;

    private double saldo;

    private double limite;

    private double credito;

    private int agencia;

    private int numero;

    private int banco;

    private Date dataCriacao;

    private boolean ativo;

    private UUID idUsuario;
}
