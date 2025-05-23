package br.com.acabouMony_usuario.repository;

import br.com.acabouMony_usuario.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByCpf(String cpf);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.nome = :nome WHERE u.id = :id")
    int atualizarNome(UUID id, String nome);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.email = :email WHERE u.id = :id")
    int atualizarEmail(UUID id, String email);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.senha = :senha WHERE u.id = :id")
    int atualizarSenha(UUID id, String senha);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.telefone = :telefone WHERE u.id = :id")
    int atualizarTelefone(UUID id, String telefone);

}
