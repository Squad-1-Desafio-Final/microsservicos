package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CadastroProdutoDto;
import br.com.acabouMony_pedido.entity.Produto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T15:33:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ProdutoMapperStructImpl implements ProdutoMapperStruct {

    @Override
    public Produto toEntity(CadastroProdutoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Produto produto = new Produto();

        return produto;
    }

    @Override
    public CadastroProdutoDto toProdutoDto(Produto entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;
        BigDecimal preco = null;
        String descricao = null;
        Byte disponivel = null;
        Integer quantidade = null;

        CadastroProdutoDto cadastroProdutoDto = new CadastroProdutoDto( nome, preco, descricao, disponivel, quantidade );

        return cadastroProdutoDto;
    }

    @Override
    public CadastroProdutoDto toDadosProdutoDto(Produto entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;
        BigDecimal preco = null;
        String descricao = null;
        Byte disponivel = null;
        Integer quantidade = null;

        CadastroProdutoDto cadastroProdutoDto = new CadastroProdutoDto( nome, preco, descricao, disponivel, quantidade );

        return cadastroProdutoDto;
    }
}
