package br.com.acabouMony_usuario.controller;

import br.com.acabouMony_usuario.dto.CadastroEnderecoDTO;
import br.com.acabouMony_usuario.entity.Endereco;
import br.com.acabouMony_usuario.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<String> cadastrarEndereco(@RequestBody @Valid CadastroEnderecoDTO enderecoDTO) {
        try {
            enderecoService.saveEndereco(enderecoDTO);
            return ResponseEntity.status(201).body("Cadastro de endereço feito com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable UUID id) {
        try {
            enderecoService.deleteEndereco(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        var listaEnderecos = enderecoService.listarTodos();

        if (listaEnderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(listaEnderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> listarUmProduto(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(enderecoService.listarUmEndereco(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarLogradouro(@PathVariable UUID id, @RequestBody Map<String, String> updates) {

        String logradouro = updates.get("logradouro");
        if (logradouro == null || logradouro.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        enderecoService.atualizarLogradouro(id, logradouro);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/numero")
    public ResponseEntity<Void> atualizarNumero(@PathVariable UUID id, @RequestBody Map<String, Integer> updates) {
        Integer numero = updates.get("numero");
        if (numero == null) {
            return ResponseEntity.badRequest().build();
        }
        enderecoService.atualizarNumero(id, numero);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/complemento")
    public ResponseEntity<Void> atualizarComplemento(@PathVariable UUID id, @RequestBody Map<String, String> updates) {
        String complemento = updates.get("complemento");
        if (complemento == null || complemento.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        enderecoService.atualizarComplemento(id, complemento);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/bairro")
    public ResponseEntity<Void> atualizarBairro(@PathVariable UUID id, @RequestBody Map<String, String> updates) {
        String bairro = updates.get("bairro");
        if (bairro == null || bairro.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        enderecoService.atualizarBairro(id, bairro);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/cidade")
    public ResponseEntity<Void> atualizarCidade(@PathVariable UUID id, @RequestBody Map<String, String> updates) {
        String cidade = updates.get("cidade");
        if (cidade == null || cidade.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        enderecoService.atualizarCidade(id, cidade);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> atualizarEstado(@PathVariable UUID id, @RequestBody Map<String, String> updates) {
        String estado = updates.get("estado");
        if (estado == null || estado.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        enderecoService.atualizarEstado(id, estado);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/cep")
    public ResponseEntity<Void> atualizarCEP(@PathVariable UUID id, @RequestBody Map<String, String> updates) {
        String cep = updates.get("cep");
        if (cep == null || cep.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        enderecoService.atualizarCEP(id, cep);
        return ResponseEntity.ok().build();
    }
}

