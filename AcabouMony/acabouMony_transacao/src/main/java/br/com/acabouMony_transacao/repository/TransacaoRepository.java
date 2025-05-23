package br.com.acabouMony_transacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<br.com.acabouMony_transacao.entity.Transacao, UUID> {
}
