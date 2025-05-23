package br.com.acabouMony_usuario.repository;

import br.com.acabouMony_usuario.entity.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e set e.logradouro = :logradouro WHERE e.id = :id")
    int atualizarLogradouro(UUID id , String logradouro);

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e set e.numero = :numero WHERE e.id = :id")
    int atualizarNumero(UUID id , int numero);

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e set e.complemento = :complemento WHERE e.id = :id")
    int atualizarComplemento(UUID id , String complemento);

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e set e.bairro = :bairro WHERE e.id = :id")
    int atualizarBairro(UUID id , String bairro);

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e set e.cidade = :cidade WHERE e.id = :id")
    int atualizarCidade(UUID id , String cidade);

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e set e.estado = :estado WHERE e.id = :id")
    int atualizarEstado(UUID id , String estado);

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e set e.cep = :cep WHERE e.id = :id")
    int atualizarCEP(UUID id , String cep);

}
