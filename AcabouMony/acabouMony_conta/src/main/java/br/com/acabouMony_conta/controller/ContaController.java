package br.com.acabouMony_conta.controller;

import br.com.acabouMony_conta.dto.AtualizacaoContaDTO;
import br.com.acabouMony_conta.dto.CadastroContaDTO;
import br.com.acabouMony_conta.dto.ListagemContaDTO;
import br.com.acabouMony_conta.entity.Conta;
import br.com.acabouMony_conta.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ListagemContaDTO> saveConta(@RequestBody @Valid CadastroContaDTO dto) {
        try {
            var conta = contaService.saveConta(dto);
            return ResponseEntity.status(201).body(conta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ListagemContaDTO>> getAllContas() {
        try {
            var conta = contaService.getAllContas();
            return ResponseEntity.status(200).body(conta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(204).build();
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Conta> getContaByUserId(@PathVariable UUID id) {

        return ResponseEntity.status(200).body(contaService.getContaByUserId(id));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemContaDTO> getOneConta(@PathVariable UUID id) {
        try {
            var conta = contaService.getOneConta(id);
            return ResponseEntity.status(200).body(conta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ListagemContaDTO> updateConta(@PathVariable UUID id, @RequestBody AtualizacaoContaDTO dto) {
        try {
            var conta = contaService.updateConta(id, dto);
            return ResponseEntity.status(200).body(conta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable UUID id) {
        try {
            contaService.deleteConta(id);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/adicionar-credito/{idConta}/{valor}")
    public ResponseEntity<Void> adicionarCredito(@PathVariable UUID idConta, @PathVariable Double valor) {
        try {
            contaService.adicionarCredito(idConta, valor);
            return ResponseEntity.status(200).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("adicionar-debito/{idConta}/{valor}")
    public ResponseEntity<Void> adicionarDebito(@PathVariable UUID idConta, @PathVariable Double valor) {
        try {
            contaService.adicionarDebito(idConta, valor);
            return ResponseEntity.status(200).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
