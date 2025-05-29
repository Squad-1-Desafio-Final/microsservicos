package br.com.acabouMony_usuario.entity;

import br.com.acabouMony_usuario.dto.RegisterDTO;
import br.com.acabouMony_usuario.dto.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private String login;

    private String password;

    private String cpf;

    private String telefone;

    private Date dtNasc;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    public Usuario(RegisterDTO dto) {
        this.role = UserRole.getRole(dto.role());
        this.password = new BCryptPasswordEncoder().encode(dto.senha());
        this.login = dto.login();
        this.cpf = dto.cpf();
        this.dtNasc = dto.dtNasc();
        this.nome = dto.nome();
        this.telefone = dto.telefone();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

