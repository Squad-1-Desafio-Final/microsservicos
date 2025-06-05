package br.com.acabouMony_conta.service;

import br.com.acabouMony_conta.dto.CadastroCartaoDTO;
import br.com.acabouMony_conta.dto.ListagemCartaoDTO;
import br.com.acabouMony_conta.entity.Cartao;
import br.com.acabouMony_conta.mapper.CartaoMapper;
import br.com.acabouMony_conta.repository.CartaoRepository;
import br.com.acabouMony_conta.tipos.TipoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CartaoMapper cartaoMapper;

    @Autowired
    private RestTemplate restTemplate;

    private static final Set<String> numerosGerados = new HashSet<>();
    private static final Random random = new Random();
    private static final DateTimeFormatter FORMATADOR_MM_YY = DateTimeFormatter.ofPattern("MM/yy");

    public ListagemCartaoDTO saveCartao(CadastroCartaoDTO dto) {

        var requisicaoURL = "http://localhost:8081/conta/" + dto.idConta();

        var conta = restTemplate.getForObject(requisicaoURL, Object.class);

        var cartao = new Cartao();
        cartao.setIdConta(dto.idConta());
        cartao.setAtivo(true);
        cartao.setNumero(gerarNumeroCartao());
        cartao.setCvv(gerarCVV());
        cartao.setValidade(gerarDataValidadeFormatada());
        cartao.setTipo(TipoPagamento.DEBITO);
        cartaoRepository.save(cartao);

        return cartaoMapper.toDto(cartao);
    }


    public List<ListagemCartaoDTO> getAllCartoes() {
        var listaCartoes = cartaoRepository.findAll();

        if (listaCartoes.isEmpty()) {
            throw new RuntimeException("Nenhuma conta cadastrada");
        }

        var listaListagemCartoes = new ArrayList<ListagemCartaoDTO>();

        listaCartoes.stream().forEach(
                c -> listaListagemCartoes.add(cartaoMapper.toDto(c)));

        return listaListagemCartoes;
    }


    public ListagemCartaoDTO getOneCartao(UUID id) {
        var cartao = cartaoRepository.findById(id);

        if (cartao.isEmpty()) {
            throw new RuntimeException("Conta com esse ID não existe!");
        }

        return cartaoMapper.toDto(cartao.get());
    }


    public void desativarCartao(UUID id) {

        if (!cartaoRepository.existsById(id)) {
            throw new RuntimeException("Cartão com esse ID não existe");
        }

        cartaoRepository.delecaoLogica(id);
    }


    public void deleteCartao(UUID id) {

        if (!cartaoRepository.existsById(id)) {
            throw new RuntimeException("Cartão com esse ID não existe");
        }

        cartaoRepository.deleteById(id);
    }


    public static String gerarDataValidadeFormatada() {
        long mesesAdicionais = ThreadLocalRandom.current().nextLong(36, 61);
        YearMonth validade = YearMonth.now().plusMonths(mesesAdicionais);
        return validade.format(FORMATADOR_MM_YY);
    }

    public static String gerarNumeroCartao() {
        String numero;
        do {
            numero = String.format("%04d%04d%04d%04d",
                    random.nextInt(10000),
                    random.nextInt(10000),
                    random.nextInt(10000),
                    random.nextInt(10000));
        } while (numerosGerados.contains(numero));
        numerosGerados.add(numero);
        return numero;
    }

    public static int gerarCVV() {
        return 100 + random.nextInt(900);
    }

    public Cartao getOneCartaoByContaId(UUID id) {

        return cartaoRepository.findByIdConta(id);

    }
}
