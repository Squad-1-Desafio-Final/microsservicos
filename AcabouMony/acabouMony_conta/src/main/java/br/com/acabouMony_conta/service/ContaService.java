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
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Conta conta = contaMapper.toEntityCad(dto);

        conta.setDataCriacao(Date.valueOf(LocalDate.now()));
        conta.setAtivo(true);
        conta.setIdUsuario(dto.idUsuario());

        var contaSalva =  contaRepository.save(conta);
        kafkaTemplate.send("conta_topico", "Conta foi aberta! com o número: " + dto.numero());

        cartaoService.saveCartao(new CadastroCartaoDTO(contaSalva.getIdConta()));

        return contaMapper.toDto(conta);

    }

    public List<ListagemContaDTO> getAllContas() {
        List<Conta> listaContas = contaRepository.findAll();

        if (listaContas.isEmpty()) {
            throw new RuntimeException("Nenhuma conta cadastrada");
        }

        return listaContas.stream()
                .map(conta -> contaMapper.toDto(conta))
                .collect(Collectors.toList());
    }

    public ListagemContaDTO getOneConta(UUID id) {
        var conta = contaRepository.findById(id);

        if (conta.isEmpty()) {
            throw new RuntimeException("Conta com esse ID não existe!");
        }

        return contaMapper.toDto(conta.get());
    }

    public ListagemContaDTO updateConta(UUID id, AtualizacaoContaDTO dto) {
        var contaEncontrada = contaRepository.findById(id);

        if (contaEncontrada.isEmpty()) {
            throw new RuntimeException("Conta com esse ID não existe!");
        }

        var conta = contaEncontrada.get();
        conta.setLimite(dto.limite());
        contaRepository.save(conta);
        return contaMapper.toDto(conta);
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

    public Conta getContaByUserId(UUID id) {

        Conta conta = contaRepository.getContaByUserId(id);

        if(conta == null){
            throw new RuntimeException("Conta não existe com esse usuário id");
        }

        return conta;
    }

    public void adicionarCredito(UUID idConta, Double valor) {

        Conta contEncontrada = contaRepository.findById(idConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta com ID " + idConta + " não foi encontrada"));

        contEncontrada.setIdConta(idConta);
        contEncontrada.setCredito(contEncontrada.getCredito() + valor);

        contaRepository.save(contEncontrada);

    }

    public void adicionarDebito(UUID idConta, Double valor) {
        Conta contEncontrada = contaRepository.findById(idConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta com ID " + idConta + " não foi encontrada"));

        contEncontrada.setIdConta(idConta);
        contEncontrada.setSaldo(contEncontrada.getSaldo() - valor);

        contaRepository.save(contEncontrada);
    }
}
