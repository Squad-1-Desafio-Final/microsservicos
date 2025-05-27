package br.com.acabouMony_pedido.service;

import br.com.acabouMony_pedido.dto.UsuarioResumoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarConfirmacaoPedido(UsuarioResumoDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.email());
        message.setFrom("eduardomiyasaki23@gmail.com\n");
        message.setSubject("Confirmação de Pedido");
        message.setText("Olá " + dto.nome() + ",\n\nSeu pedido foi realizado com sucesso!");

        mailSender.send(message);
        System.out.println("E-mail enviado com sucesso para " + dto.email());
    }
}

