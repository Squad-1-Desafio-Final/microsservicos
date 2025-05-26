package br.com.acabouMony_conta.mapper;

import br.com.acabouMony_conta.dto.CadastroContaDTO;
import br.com.acabouMony_conta.dto.ListagemContaDTO;
import br.com.acabouMony_conta.entity.Conta;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T09:54:16-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ContaMapperImpl implements ContaMapper {

    @Override
    public Conta toEntity(CadastroContaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Conta conta = new Conta();

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

        ListagemContaDTO listagemContaDTO = new ListagemContaDTO( dataVencimento, limite, agencia, numero );

        return listagemContaDTO;
    }
}
