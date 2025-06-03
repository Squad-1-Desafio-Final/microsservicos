package br.com.acabouMony_pedido.mapper;
import br.com.acabouMony_pedido.dto.UsuarioResumoDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResumoMapper {

    public static UsuarioResumoDto toDto(UsuarioResumoDto usuario) {
        if (usuario == null) {
            return null; // ou lance exceção se preferir
        }

        return new UsuarioResumoDto(
                usuario.id(),
                usuario.nome(),
                usuario.login()
        );
    }
}
