package br.com.acabouMony_pedido.mapper;

import br.com.acabouMony_pedido.dto.CadastroProdutoDto;
import br.com.acabouMony_pedido.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    // Converte DTO para entidade Produto (para criar ou atualizar)
    public Produto toEntity(CadastroProdutoDto dto) {
        if (dto == null) {
            return null;
        }

        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setDescricao(dto.descricao());
        produto.setDisponivel(dto.disponivel());
        produto.setQuantidade(dto.quantidade());

        return produto;
    }

    // Opcional: converte entidade para DTO (se precisar)
    public CadastroProdutoDto toDto(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new CadastroProdutoDto(
                produto.getNome(),
                produto.getPreco(),
                produto.getDescricao(),
                produto.getDisponivel(),
                produto.getQuantidade()
        );
    }
}
