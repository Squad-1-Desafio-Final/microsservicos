package br.com.acabouMony_transacao.service;

import br.com.acabouMony_transacao.dto.PedidoResumoDto;
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

    public void enviarConfirmacaoTransacao(PedidoResumoDto pedido, UsuarioResumoDto usuario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(usuario.login());
        message.setSubject("Confirmação de Transação Concluída");

        String corpo = "Olá " + usuario.nome() + " 😊\n\n" +
                "Sua transação foi concluída com sucesso! 💳\n\n" +
                "🧾 **Resumo do Pedido:**\n" +
                "- Valor total: R$ " + String.format("%.2f", pedido.precoTotal()) + "\n" +
                "- Número do pedido: " + pedido.id() + "\n\n" +
                "Agradecemos pela sua compra!\n" +
                "Se precisar de qualquer ajuda, estamos à disposição.\n\n" +
                "Atenciosamente,\n" +
                "Acabou Mony";

        message.setText(corpo);
        mailSender.send(message);
        System.out.println("E-mail enviado com sucesso para " + usuario.login());
    }
}
