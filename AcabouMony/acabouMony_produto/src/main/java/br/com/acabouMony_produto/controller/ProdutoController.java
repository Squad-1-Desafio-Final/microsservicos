package br.com.acabouMony_produto.controller;


import br.com.acabouMony_produto.dto.CadastroProdutoDto;
import br.com.acabouMony_produto.entity.Produto;
import br.com.acabouMony_produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService service;


    @PostMapping("/criar")
    public ResponseEntity<CadastroProdutoDto> criandoProduto(@RequestBody CadastroProdutoDto dados ){
        return ResponseEntity.status(201).body(service.criar(dados));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<CadastroProdutoDto> listarProduto(@PathVariable UUID idProduto){
        return ResponseEntity.status(200).body(service.listar(idProduto));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<CadastroProdutoDto> atualizarProduto(@PathVariable UUID idProduto, @RequestBody CadastroProdutoDto dto){
        return ResponseEntity.status(200).body(service.atualizar(idProduto,dto));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID idProduto){
        service.deletar(idProduto);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CadastroProdutoDto>> listarTodos(){

        return ResponseEntity.status(200).body(service.listarTodos());

    }

    @GetMapping("/findAllById/{id}")
    public ResponseEntity<List<Produto>> listarPorId(@PathVariable List<UUID> idProduto){
        return ResponseEntity.status(200).body(service.listarId(idProduto));
    }





}
