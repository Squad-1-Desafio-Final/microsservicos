package br.com.acabouMony_usuario.dto;

import java.util.UUID;

public record CadastroEnderecoDTO(
        String logradouro,
        int numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        UUID idUsuario
) {
}
