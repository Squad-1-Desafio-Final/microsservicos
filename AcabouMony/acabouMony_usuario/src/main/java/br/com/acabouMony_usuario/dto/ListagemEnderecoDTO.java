package br.com.acabouMony_usuario.dto;

public record ListagemEnderecoDTO(
        String logradouro,
        int numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {

}
