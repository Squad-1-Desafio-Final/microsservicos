package br.com.acabouMony_usuario.dto;

import java.time.LocalDate;
import java.util.Date;

public record RegisterDTO(String login, String password, String role, String cpf, Date dtNasc, String nome, String telefone) {
}
