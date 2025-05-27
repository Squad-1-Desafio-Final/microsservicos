package br.com.acabouMony_conta.service;

import br.com.acabouMony_conta.dto.AtualizacaoContaDTO;
import br.com.acabouMony_conta.dto.CadastroCartaoDTO;
import br.com.acabouMony_conta.dto.CadastroContaDTO;
import br.com.acabouMony_conta.dto.ListagemContaDTO;
import br.com.acabouMony_conta.entity.Conta;
import br.com.acabouMony_conta.mapper.ContaMapper;
import br.com.acabouMony_conta.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaMapper contaMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private CartaoService cartaoService;

    public ListagemContaDTO saveConta(CadastroContaDTO dto) {

        var requisicaoURL = "http://localhost:8084/usuario/" + dto.idUsuario();

        var usuario = restTemplate.getForObject(requisicaoURL, Object.class);

        if (contaRepository.existsByNumero(dto.numero())) {
            throw new RuntimeException("Conta com esse número já existe");
        }

        if (contaRepository.existsByIdUsuario(dto.idUsuario())) {
            throw new RuntimeException("Conta com esse Usuário já existe");
        }

        Conta conta = contaMapper.toEntity(dto);

        conta.setDataCriacao(Date.valueOf(LocalDate.now()));
        conta.setAtivo(true);
        conta.setIdUsuario(dto.idUsuario());

        var contaSalva =  contaRepository.save(conta);
        kafkaTemplate.send("conta_topico", "Conta foi aberta! com o número: " + dto.numero());

        cartaoService.saveCartao(new CadastroCartaoDTO(contaSalva.getIdConta()));

        return contaMapper.toListagemContaDTO(conta);

    }

    public List<ListagemContaDTO> getAllContas() {
        var listaContas = contaRepository.findAll();

        if (listaContas.isEmpty()) {
            throw new RuntimeException("Nenhuma conta cadastrada");
        }

        var listaListagemConta = new ArrayList<ListagemContaDTO>();

        listaContas.stream().forEach(
                c -> listaListagemConta.add(contaMapper.toListagemContaDTO(c)));

        return listaListagemConta;
    }

    public ListagemContaDTO getOneConta(UUID id) {
        var conta = contaRepository.findById(id);

        if (conta.isEmpty()) {
            throw new RuntimeException("Conta com esse ID não existe!");
        }

        return contaMapper.toListagemContaDTO(conta.get());
    }

    public ListagemContaDTO updateConta(UUID id, AtualizacaoContaDTO dto) {
        var contaEncontrada = contaRepository.findById(id);

        if (contaEncontrada.isEmpty()) {
            throw new RuntimeException("Conta com esse ID não existe!");
        }

        var conta = contaEncontrada.get();
        conta.setLimite(dto.limite());
        contaRepository.save(conta);
        return contaMapper.toListagemContaDTO(conta);
    }

    public void deleteConta(UUID id) {

        if (!contaRepository.existsById(id)) {
            throw new RuntimeException("Conta com esse ID não existe");
        }

        contaRepository.deleteById(id);
    }

    public void deleteLogicaConta(UUID id) {

        if (!contaRepository.existsById(id)) {
            throw new RuntimeException("Conta com esse ID não existe");
        }

        contaRepository.delecaoLogica(id);
    }
}
