package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.CadastroContaDTO;
import br.com.acabouMony_conta.dto.ListagemContaDTO;
import br.com.acabouMony_conta.entity.Conta;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class ContaMapper {

    // Entidade -> DTO
    public ListagemContaDTO toDto(Conta conta) {
        return new ListagemContaDTO(
                conta.getDataCriacao(),
                conta.getLimite(),
                conta.getAgencia(),
                conta.getNumero()
        );
    }

    // DTO -> Entidade
    public Conta toEntityList(ListagemContaDTO dto) {
        Conta conta = new Conta();
        conta.setDataVencimento(dto.dataVencimento().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()); // Date -> LocalDate
        conta.setLimite(dto.limite());
        conta.setAgencia(dto.agencia());
        conta.setNumero(dto.numero());

        // Os demais campos (saldo, banco, dataCriacao, ativo, etc.)
        // devem ser preenchidos em outro contexto, se necessário.

        return conta;
    }

    // DTO -> Entidade
    public Conta toEntityCad(CadastroContaDTO dto) {
        Conta conta = new Conta();
        conta.setDataVencimento(dto.dataVencimento());
        conta.setLimite(dto.limite());
        conta.setAgencia(dto.agencia());
        conta.setNumero(dto.numero());
        conta.setBanco(dto.banco());
        conta.setIdUsuario(dto.idUsuario());

        // Valores padrão para novos cadastros
        conta.setSaldo(0.0);
        conta.setCredito(0.0);
        conta.setDataCriacao(new java.util.Date());
        conta.setAtivo(true);

        return conta;
    }
}
