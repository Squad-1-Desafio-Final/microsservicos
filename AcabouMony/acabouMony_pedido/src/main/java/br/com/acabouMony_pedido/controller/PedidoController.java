package br.com.acabouMony_pedido.controller;

import br.com.acabouMony_pedido.dto.AtualizacaoPedidoDTO;
import br.com.acabouMony_pedido.dto.CadastroPedidoDto;
import br.com.acabouMony_pedido.dto.ConcluirTransacaDto;
import br.com.acabouMony_pedido.dto.ListagemPedidoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import br.com.acabouMony_pedido.service.PedidoService;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService service;

    @GetMapping
    public ResponseEntity<List<ListagemPedidoDto>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemPedidoDto> listarPorId(@PathVariable  UUID id){
        return ResponseEntity.status(200).body(service.listarPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<ListagemPedidoDto> criar(@RequestBody CadastroPedidoDto dados){
        return ResponseEntity.status(201).body(service.criar(dados));
    }

    @PatchMapping("/concluir-transacao")
    public ResponseEntity<ListagemPedidoDto> concluirTransacao(@RequestBody ConcluirTransacaDto dados){
        try {
            return ResponseEntity.status(200).body(service.concluirTransacao(dados));
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("--------------a");
        }
        return null;
    }

    @PatchMapping("/editar/{id}")
    public ResponseEntity<ListagemPedidoDto> editar(@PathVariable UUID id, @RequestBody AtualizacaoPedidoDTO dados){
        return ResponseEntity.status(200).body(service.editar(id, dados));
    }

}
