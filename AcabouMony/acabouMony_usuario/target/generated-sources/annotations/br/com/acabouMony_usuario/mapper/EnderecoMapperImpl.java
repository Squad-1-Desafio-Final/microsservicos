package br.com.acabouMony_usuario.mapper;

import br.com.acabouMony_usuario.dto.CadastroEnderecoDTO;
import br.com.acabouMony_usuario.dto.ListagemEnderecoDTO;
import br.com.acabouMony_usuario.entity.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T15:14:15-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco toEntity(CadastroEnderecoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setNumero( dto.numero() );
        endereco.setLogradouro( dto.logradouro() );
        endereco.setComplemento( dto.complemento() );
        endereco.setBairro( dto.bairro() );
        endereco.setCidade( dto.cidade() );
        endereco.setEstado( dto.estado() );
        endereco.setCep( dto.cep() );

        return endereco;
    }

    @Override
    public ListagemEnderecoDTO toListagemEnderecoDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        String logradouro = null;
        int numero = 0;
        String complemento = null;
        String bairro = null;
        String cidade = null;
        String estado = null;
        String cep = null;

        logradouro = endereco.getLogradouro();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        cidade = endereco.getCidade();
        estado = endereco.getEstado();
        cep = endereco.getCep();

        ListagemEnderecoDTO listagemEnderecoDTO = new ListagemEnderecoDTO( logradouro, numero, complemento, bairro, cidade, estado, cep );

        return listagemEnderecoDTO;
    }
}
