package br.com.acabouMony_transacao.controller;

import br.com.acabouMony_transacao.dto.CadastroTransacaoDto;
import br.com.acabouMony_transacao.dto.ListagemTransacaoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import br.com.acabouMony_transacao.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {


    @Autowired
    TransacaoService service;

    @GetMapping
    public ResponseEntity<List<ListagemTransacaoDto>> listar() {
        return ResponseEntity.status(200).body(service.listar());
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroTransacaoDto dados) {
        try {
            return ResponseEntity.status(201).body(service.criar(dados));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
