package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.CadastroContaDTO;
import br.com.acabouMony_conta.dto.ListagemContaDTO;
import br.com.acabouMony_conta.entity.Conta;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T15:36:31-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class ContaMapperImpl implements ContaMapper {

    @Override
    public Conta toEntity(CadastroContaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Conta conta = new Conta();

        conta.setNumero( dto.numero() );
        conta.setDataVencimento( dto.dataVencimento() );
        conta.setLimite( dto.limite() );
        conta.setAgencia( dto.agencia() );
        conta.setBanco( dto.banco() );
        conta.setIdUsuario( dto.idUsuario() );

        return conta;
    }

    @Override
    public ListagemContaDTO toListagemContaDTO(Conta conta) {
        if ( conta == null ) {
            return null;
        }

        Date dataVencimento = null;
        double limite = 0.0d;
        int agencia = 0;
        int numero = 0;

        if ( conta.getDataVencimento() != null ) {
            dataVencimento = Date.from( conta.getDataVencimento().atStartOfDay( ZoneOffset.UTC ).toInstant() );
        }
        limite = conta.getLimite();
        agencia = conta.getAgencia();
        numero = conta.getNumero();

        ListagemContaDTO listagemContaDTO = new ListagemContaDTO( dataVencimento, limite, agencia, numero );

        return listagemContaDTO;
    }
}
