package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.CadastroCartaoDTO;
import br.com.acabouMony_conta.dto.ListagemCartaoDTO;
import br.com.acabouMony_conta.entity.Cartao;
import br.com.acabouMony_conta.tipos.TipoPagamento;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T15:36:31-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class CartaoMapperImpl implements CartaoMapper {

    @Override
    public Cartao toEntity(CadastroCartaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cartao cartao = new Cartao();

        if ( dto.senha() != null ) {
            cartao.setSenha( dto.senha() );
        }
        cartao.setIdConta( dto.idConta() );

        return cartao;
    }

    @Override
    public ListagemCartaoDTO toCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        String numero = null;
        TipoPagamento tipo = null;

        numero = entity.getNumero();
        tipo = entity.getTipo();

        ListagemCartaoDTO listagemCartaoDTO = new ListagemCartaoDTO( numero, tipo );

        return listagemCartaoDTO;
    }
}
