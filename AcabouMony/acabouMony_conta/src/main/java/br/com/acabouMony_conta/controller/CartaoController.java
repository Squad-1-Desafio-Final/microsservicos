package br.com.acabouMony_conta.controller;

import br.com.acabouMony_conta.dto.CadastroCartaoDTO;
import br.com.acabouMony_conta.dto.ListagemCartaoDTO;
import br.com.acabouMony_conta.service.CartaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<ListagemCartaoDTO> saveCartao(@RequestBody @Valid CadastroCartaoDTO dto){
        try {
            var cartao = cartaoService.saveCartao(dto);
            return ResponseEntity.status(201).body(cartao);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ListagemCartaoDTO>> getAllCartoes() {
        try {
            var cartoes = cartaoService.getAllCartoes();
            return ResponseEntity.status(200).body(cartoes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(204).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemCartaoDTO> getOneCartao(@PathVariable UUID id) {
        try {
            var cartao = cartaoService.getOneCartao(id);
            return ResponseEntity.status(200).body(cartao);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Void> desativarCartao(@PathVariable UUID id) {
        try {
            cartaoService.desativarCartao(id);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartao(@PathVariable UUID id) {
        try {
            cartaoService.deleteCartao(id);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
