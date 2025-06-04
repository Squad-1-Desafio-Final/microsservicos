package br.com.acabouMony_transacao.service;

import br.com.acabouMony_transacao.dto.TransacaoResumoDto;
import br.com.acabouMony_transacao.dto.UsuarioResumoDto;
import br.com.acabouMony_transacao.entity.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceTransacao {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarConfirmacaoTransacao(UsuarioResumoDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.login());
        message.setFrom("monicajleamari@gmail.com\n");
        message.setSubject("Confirmação de Transição");
        message.setText("Olá " + dto.nome() + " \uD83D\uDE0A\n" +
                "\n" +
                "Sua transação foi concluída com sucesso! \uD83D\uDCB8\n" +
                "\n" +
                "Caso precise de algo mais, estamos à disposição para ajudar. Conte conosco! ");

        mailSender.send(message);
        System.out.println("E-mail enviado com sucesso para " + dto.login());
    }
}
