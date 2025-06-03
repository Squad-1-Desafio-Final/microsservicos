package br.com.acabouMony_produto.mapper;

import br.com.acabouMony_produto.dto.CadastroProdutoDto;
import br.com.acabouMony_produto.entity.Produto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-03T13:26:20-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class ProdutoMapperStructImpl implements ProdutoMapperStruct {

    @Override
    public Produto toEntity(CadastroProdutoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setNome( dto.nome() );
        produto.setPreco( dto.preco() );
        produto.setDescricao( dto.descricao() );
        produto.setDisponivel( dto.disponivel() );
        produto.setQuantidade( dto.quantidade() );

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

        nome = entity.getNome();
        preco = entity.getPreco();
        descricao = entity.getDescricao();
        disponivel = entity.getDisponivel();
        quantidade = entity.getQuantidade();

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

        nome = entity.getNome();
        preco = entity.getPreco();
        descricao = entity.getDescricao();
        disponivel = entity.getDisponivel();
        quantidade = entity.getQuantidade();

        CadastroProdutoDto cadastroProdutoDto = new CadastroProdutoDto( nome, preco, descricao, disponivel, quantidade );

        return cadastroProdutoDto;
    }
}
