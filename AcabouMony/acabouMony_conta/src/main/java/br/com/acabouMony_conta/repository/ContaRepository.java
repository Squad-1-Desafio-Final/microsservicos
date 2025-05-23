package br.com.acabouMony_conta.repository;

import br.com.acabouMony_conta.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByNumero(int numeroConta);

    @Modifying
    @Transactional
    @Query("UPDATE Conta c SET c.ativo = false WHERE c.id = :id")
    void delecaoLogica(UUID id);

    boolean existsByNumero(int numero);
}
