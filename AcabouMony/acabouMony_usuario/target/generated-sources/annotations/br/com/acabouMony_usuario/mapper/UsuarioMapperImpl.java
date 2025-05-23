package br.com.acabouMony_usuario.mapper;

import br.com.acabouMony_usuario.dto.CadastroUsuarioDTO;
import br.com.acabouMony_usuario.dto.ListagemUsuarioDTO;
import br.com.acabouMony_usuario.entity.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T15:14:15-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(CadastroUsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( dto.nome() );
        usuario.setEmail( dto.email() );
        usuario.setSenha( dto.senha() );
        usuario.setCpf( dto.cpf() );
        usuario.setTelefone( dto.telefone() );
        usuario.setDtNasc( dto.dtNasc() );

        return usuario;
    }

    @Override
    public ListagemUsuarioDTO toListagemUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String nome = null;
        String email = null;
        String telefone = null;
        Date dtNasc = null;

        nome = usuario.getNome();
        email = usuario.getEmail();
        telefone = usuario.getTelefone();
        dtNasc = usuario.getDtNasc();

        ListagemUsuarioDTO listagemUsuarioDTO = new ListagemUsuarioDTO( nome, email, telefone, dtNasc );

        return listagemUsuarioDTO;
    }
}
