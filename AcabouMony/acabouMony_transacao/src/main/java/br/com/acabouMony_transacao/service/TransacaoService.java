package br.com.acabouMony_transacao.service;

import br.com.acabouMony_transacao.dto.*;
import br.com.acabouMony_transacao.exception.CartaoNaoEncontrado;
import br.com.acabouMony_transacao.exception.PedidoComCarrinhoAtivoException;
import br.com.acabouMony_transacao.exception.PedidoNaoEncontrado;
import br.com.acabouMony_transacao.exception.UsuarioNaoEncontradoException;
import org.springframework.stereotype.Service;


import br.com.acabouMony_transacao.entity.*;
import br.com.acabouMony_transacao.mapper.*;
import br.com.acabouMony_transacao.repository.*;
import br.com.acabouMony_transacao.repository.TransacaoRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmailServiceTransacao emailServiceTransacao;

//    @Autowired
//    UsuarioRepository usuarioRepository;

//    @Autowired
//    CartaoRepository cartaoRepository;

//    @Autowired
//    PedidoRepository pedidoRepository;

    @Autowired
    br.com.acabouMony_transacao.mapper.TransacaoCadastroMapper transacaoCadastroMapper;

    @Autowired
    br.com.acabouMony_transacao.mapper.TransacaoListarMapper transacaoListarMapper;

    public ListagemTransacaoDto criar (CadastroTransacaoDto dados){

        CartaoResumoDto cartao = getCartaoResumo(dados);

        PedidoResumoDto pedido = getPedidoResumo(dados);


        //PedidoCarrinhoDto carrinho = restTemplate.getForObject("http://localhost:8082/pedido/" + dados.pedido(), PedidoCarrinhoDto.class);


        if (cartao == null){
            throw new CartaoNaoEncontrado("Cartão não encontrado");
        }
        if (pedido == null){
            throw new PedidoNaoEncontrado("Pedido não encontrado");
        }

        if (pedido.carrinho()) {
            throw new PedidoComCarrinhoAtivoException("O pedido ainda está com o carrinho ativo");
        }

        Transacao transacao = transacaoCadastroMapper.toEntity(dados);
        transacao.setData(new Date(System.currentTimeMillis() + 1000));
        transacao.setIdCartao(dados.cartao());
        transacao.setIdPedido(dados.pedido());
        transacao.setIdDestinatario(dados.destinatario());
        transacao.setTipo(dados.tipo());
        Transacao transacaoSalva = repository.save(transacao);


        //emailServiceTransacao.enviarConfirmacaoTransacao(transacaoSalva);

        return transacaoListarMapper.toTransacaoDto(transacaoSalva);

    }

    public List<ListagemTransacaoDto> listar (){

        List<Transacao> transacoes = repository.findAll();

        return transacoes.stream()
                .map(transacaoListarMapper::toTransacaoDto)
                .collect(Collectors.toList());
    }



    public CartaoResumoDto getCartaoResumo(CadastroTransacaoDto dados){
        return restTemplate.getForObject("http://localhost:8081/cartao/"+ dados.cartao(), CartaoResumoDto.class);
    }

    public PedidoResumoDto getPedidoResumo(CadastroTransacaoDto dados){
        return restTemplate.getForObject("http://localhost:8082/pedido/" + dados.pedido(), PedidoResumoDto.class);
    }

}
