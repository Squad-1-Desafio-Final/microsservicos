package br.com.acabouMony_pedido.service;

import br.com.acabouMony_pedido.dto.*;
import br.com.acabouMony_pedido.entity.Pedido;
import br.com.acabouMony_pedido.entity.Produto;
import br.com.acabouMony_pedido.exception.*;
import br.com.acabouMony_pedido.mapper.CadastroPedidoMapper;
import br.com.acabouMony_pedido.mapper.ListagemPedidoMapper;
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
    CadastroPedidoMapper pedidoCadastrarMapper;


    @Autowired
    ListagemPedidoMapper pedidoListarMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmailService emailService;

    public List<ListagemPedidoDto> listar(){
        List<Pedido> pedidos = repository.findAll();

        return pedidos.stream()
                .map(pedidoListarMapper::toDto)
                .collect(Collectors.toList());

    }

    public ListagemPedidoDto listarPorId(UUID id){
        Pedido pedidos = repository.findById(id).orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        return pedidoListarMapper.toDto(pedidos);

    }

    public ListagemPedidoDto criar(CadastroPedidoDto dados){
        UsuarioResumoDto usuario = restTemplate.getForObject("http://localhost:8084/usuario/" + dados.usuario(), UsuarioResumoDto.class);

        Pedido pedido = pedidoCadastrarMapper.toEntity(dados);
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

        return pedidoListarMapper.toDto(pedidoSalvo);

    }

    public ListagemPedidoDto editar(UUID id, Pedido dados){

        Pedido pedidoEncontrado = repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        if(!pedidoEncontrado.getCarrinho()){
            throw new PedidoNaoPodeSerEditadoException("O pedido não pode ser editado porque está em uma transação");
        }

        Pedido pedidoSavo = repository.save(pedidoEncontrado);

        return pedidoListarMapper.toDto(pedidoSavo);

    }

    public ListagemPedidoDto concluirTransacao(ConcluirTransacaDto dto){

        Pedido pedidoEncontrado = repository.findById(dto.idPedido())
                .orElseThrow(() -> new PedidoNaoPodeSerEditadoException("Pedido não encontrado"));

        UsuarioResumoDto usuarioResumoDto = restTemplate.getForObject("http://localhost:8084/usuario/" + dto.idUsuario(), UsuarioResumoDto.class);

        ContaDto conta = restTemplate.getForObject("http://localhost:8080/conta/usuario/" + dto.idUsuario(), ContaDto.class);

        CartaoDto cartao = restTemplate.getForObject("http://localhost:8080/cartao/conta/" + conta.idConta(), CartaoDto.class);

        //Verificando tipo de pagamento pedido e cartão
        String tipoCartao = cartao.tipo();
        String tipoPedido = pedidoEncontrado.getTipo().toString();

        if (!tipoPedido.equalsIgnoreCase(tipoCartao)){
            throw new TransacaoRecusadaTipo("Transação não pode ser confirmada, cartão não é do tipo " + pedidoEncontrado.getTipo());
        }

        //logica de credito
        if (tipoPedido.equalsIgnoreCase("CREDITO") && tipoCartao.equalsIgnoreCase("CREDITO")){
            Double disponivel = conta.limite() - conta.credito();

            if (disponivel < pedidoEncontrado.getPrecoTotal()){
                throw new CreditoInsuficienteException("O valor disponivel de crédito é insuficiente");
            }

          //restTemplate.exchange("http://localhost:8080/conta/adicionar-credito/" + conta.idConta() + "/" + pedidoEncontrado.getPrecoTotal());

            restTemplate.getForObject("http://localhost:8080/conta/adicionar-credito/" + conta.idConta() + "/" + pedidoEncontrado.getPrecoTotal(), Void.class);





        }else{

            if(conta.saldo() < pedidoEncontrado.getPrecoTotal()){
                throw new SaldoInsuficienteExcepetion("Saldo insuficiente");
            }

            restTemplate.getForObject("http://localhost:8080/conta/adicionar-debito/" + conta.idConta() + "/" + pedidoEncontrado.getPrecoTotal(), Void.class);



            //restTemplate.getForObject("http://localhost:8080/conta/valor-debito/" + conta.idConta() + "/" + pedidoEncontrado.getPrecoTotal(), ContaDto.class);

        }
        for (Produto produto : pedidoEncontrado.getProdutos()) {
            Produto produtoBanco = produtoRepository.findByIdProduto(produto.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produtoBanco.getQuantidade() < 1) {
                throw new EstoqueInsuficienteException("Produto " + produtoBanco.getNome() + " está fora de estoque.");
            }

            // Reduz o estoque (caso queira já alterar o produto)
            produtoBanco.setQuantidade(produtoBanco.getQuantidade() - 1);
            produtoRepository.save(produtoBanco);
        }


        pedidoEncontrado.setCarrinho(false);
        Pedido pedidoSavo = repository.save(pedidoEncontrado);
        //assert usuarioResumoDto != null;
        //emailService.enviarConfirmacaoPedido(usuarioResumoDto);
        return pedidoListarMapper.toDto(pedidoSavo);
    }

}
