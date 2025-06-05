package br.com.acabouMony_usuario.repository;

import br.com.acabouMony_usuario.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByCpf(String cpf);

    boolean existsByLogin(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.nome = :nome WHERE u.id = :id")
    Usuario atualizarNome(UUID id, String nome);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.login = :email WHERE u.id = :id")
    Usuario atualizarLogin(UUID id, String email);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.password = :senha WHERE u.id = :id")
    Usuario atualizarpassword(UUID id, String senha);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u set u.telefone = :telefone WHERE u.id = :id")
    Usuario atualizarTelefone(UUID id, String telefone);

    UserDetails findByLogin(String login);

}
