package br.com.acabouMony_usuario.service;



import br.com.acabouMony_usuario.dto.CadastroEnderecoDTO;
import br.com.acabouMony_usuario.dto.ListagemEnderecoDTO;
import br.com.acabouMony_usuario.entity.Endereco;
import br.com.acabouMony_usuario.mapper.EnderecoMapper;
import br.com.acabouMony_usuario.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Transactional
    public ListagemEnderecoDTO saveEndereco(CadastroEnderecoDTO enderecoDTO) {

        var endereco = new Endereco(enderecoDTO);
        enderecoRepository.save(endereco);
        return enderecoMapper.toListagemEnderecoDTO(endereco);
    }

    public void deleteEndereco(UUID id) {
        var enderecoEncontrado = enderecoRepository.findById(id);

        if (enderecoEncontrado.isEmpty()) {
            throw new RuntimeException("Não há endereço cadastrada para o id " + id);
        }

        enderecoRepository.deleteById(id);
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco listarUmEndereco(UUID id) {
        var endereco = enderecoRepository.findById(id);

        if (endereco.isEmpty()) {
            throw new RuntimeException("Usuário com ID não encontrado!");
        }

        return endereco.get();
    }

    public void atualizarLogradouro(UUID id, String logradouro) {
        enderecoRepository.atualizarLogradouro(id, logradouro);
    }

    public void atualizarNumero(UUID id, int numero) {
        enderecoRepository.atualizarNumero(id, numero);
    }

    public void atualizarComplemento(UUID id, String complemento) {
        enderecoRepository.atualizarComplemento(id, complemento);
    }

    public void atualizarBairro(UUID id, String bairro) {
        enderecoRepository.atualizarBairro(id, bairro);
    }

    public void atualizarCidade(UUID id, String cidade) {
        enderecoRepository.atualizarCidade(id, cidade);
    }

    public void atualizarEstado(UUID id, String estado) {
        enderecoRepository.atualizarEstado(id, estado);
    }

    public void atualizarCEP(UUID id, String cep) {
        enderecoRepository.atualizarCEP(id, cep);
    }
}
