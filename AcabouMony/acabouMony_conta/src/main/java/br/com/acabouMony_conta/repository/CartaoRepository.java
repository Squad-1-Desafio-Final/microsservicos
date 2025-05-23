package br.com.acabouMony_conta.repository;

import br.com.acabouMony_conta.dto.ListagemCartaoDTO;
import br.com.acabouMony_conta.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CartaoRepository extends JpaRepository<Cartao, UUID> {

    // trocar no nome do database
    @Query("SELECT new br.com.acabouMony_conta.dto.ListagemCartaoDTO(c.numero, c.tipo) FROM Cartao c")
    List<ListagemCartaoDTO> listarNumETipo();

    @Modifying
    @Transactional
    @Query("UPDATE Cartao c SET c.ativo = false WHERE c.id = :id")
    void delecaoLogica(UUID id);
}
