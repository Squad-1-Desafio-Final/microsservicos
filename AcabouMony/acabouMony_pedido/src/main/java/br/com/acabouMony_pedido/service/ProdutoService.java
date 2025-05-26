package br.com.acabouMony_pedido.service;

import br.com.acabouMony_pedido.dto.CadastroProdutoDto;
import br.com.acabouMony_pedido.entity.Produto;
import br.com.acabouMony_pedido.exception.ProdutoNaoEncontrado;
import br.com.acabouMony_pedido.mapper.ProdutoMapperStruct;
import br.com.acabouMony_pedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    ProdutoMapperStruct produtoMapperStruct;

    public CadastroProdutoDto criar(CadastroProdutoDto dados){

        Produto produto1 = produtoMapperStruct.toEntity(dados);
        Produto produtoSalvo = repository.save(produto1);
        return produtoMapperStruct.toProdutoDto(produtoSalvo);
    }



    public CadastroProdutoDto listar(UUID id){
        Produto produtos = repository.findById(id).orElseThrow(() -> new ProdutoNaoEncontrado("Produto não encontrado"));

        return produtoMapperStruct.toProdutoDto(produtos);

    }

    public void deletar(UUID id){
        repository.deleteById(id);
    }

    public CadastroProdutoDto atualizar(UUID id, CadastroProdutoDto dto){
        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNaoEncontrado("Produto não encontrado"));
        produto.setDescricao(dto.descricao());
        produto.setNome(dto.nome());
        produto.setDisponivel(dto.disponivel());
        produto.setQuantidade(dto.quantidade());


        Produto produtoSalvo = repository.save(produto);
        return produtoMapperStruct.toProdutoDto(produtoSalvo);


    }

    public List<CadastroProdutoDto> listarTodos(){

        List<Produto> lista = repository.findAll();
        return lista.stream()
                .map(produtoMapperStruct::toProdutoDto)
                .collect(Collectors.toList());

    }

    public List<Produto> listarId(List<UUID> idProduto){
        List<Produto> listar = repository.findAllById(idProduto);

        return listar;
    }



}
