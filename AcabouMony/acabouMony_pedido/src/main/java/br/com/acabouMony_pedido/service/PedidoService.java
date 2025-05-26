package br.com.acabouMony_pedido.service;

import br.com.acabouMony_pedido.dto.CadastroPedidoDto;
import br.com.acabouMony_pedido.dto.ListagemPedidoDto;
import br.com.acabouMony_pedido.dto.UsuarioResumoDto;
import br.com.acabouMony_pedido.entity.Pedido;
import br.com.acabouMony_pedido.entity.Produto;
import br.com.acabouMony_pedido.exception.PedidoNaoEncontrado;
import br.com.acabouMony_pedido.exception.PedidoNaoPodeSerEditadoException;
import br.com.acabouMony_pedido.mapper.PedidoCadastrarMapperStruct;
import br.com.acabouMony_pedido.mapper.PedidoListarMapperStruct;
import br.com.acabouMony_pedido.repository.PedidoRepository;
import br.com.acabouMony_pedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoCadastrarMapperStruct pedidoCadastrarMapperStruct;


    @Autowired
    PedidoListarMapperStruct pedidoListarMapperStruct;

    @Autowired
    private RestTemplate restTemplate;

    public List<ListagemPedidoDto> listar(){
        List<Pedido> pedidos = repository.findAll();

        return pedidos.stream()
                .map(pedidoListarMapperStruct::toPedidoDto)
                .collect(Collectors.toList());

    }

    public ListagemPedidoDto listarPorId(UUID id){
        Pedido pedidos = repository.findById(id).orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        return pedidoListarMapperStruct.toPedidoDto(pedidos);

    }

    public ListagemPedidoDto criar(CadastroPedidoDto dados){
        UsuarioResumoDto usuario = restTemplate.getForObject("http://localhost:8084/usuario/" + dados.usuario(), UsuarioResumoDto.class);

        Pedido pedido = pedidoCadastrarMapperStruct.toEntity(dados);
        pedido.setDate(new Date(System.currentTimeMillis() + 1000));
        pedido.setCarrinho(true);
        pedido.setIdUsuario(usuario.id());

        List<Produto> produtos = produtoRepository.findAllById(dados.produtos());
        pedido.setProdutos(produtos);

        BigDecimal precoTotal = produtos.stream()
                .map(Produto::getPreco)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setPrecoTotal(precoTotal.doubleValue());

        Pedido pedidoSalvo = repository.save(pedido);

        return pedidoListarMapperStruct.toPedidoDto(pedidoSalvo);

    }

    public ListagemPedidoDto editar(UUID id, Pedido dados){

        Pedido pedidoEncontrado = repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        if(!pedidoEncontrado.getCarrinho()){
            throw new PedidoNaoPodeSerEditadoException("O pedido não pode ser editado porque está em uma transação");
        }

        Pedido pedidoSavo = repository.save(pedidoEncontrado);

        return pedidoListarMapperStruct.toPedidoDto(pedidoSavo);

    }

    public ListagemPedidoDto concluirTransacao(UUID id){

        Pedido pedidoEncontrado = repository.findById(id)
                .orElseThrow(() -> new PedidoNaoPodeSerEditadoException("Pedido não encontrado"));

        pedidoEncontrado.setCarrinho(false);

        Pedido pedidoSavo = repository.save(pedidoEncontrado);

        return pedidoListarMapperStruct.toPedidoDto(pedidoSavo);
    }

}
