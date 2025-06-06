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
        message.setTo(dto.login());
        message.setSubject("Confirmação de Pedido Recebido – Aguardando Finalização");

        message.setText("Olá " + dto.nome() + ",\n\n" +
                "Recebemos a criação do seu pedido em nossa loja, e ele está quase pronto para ser finalizado!\n\n" +
                "📦 Detalhes do Pedido:\n" +
                "Para concluir sua compra, basta acessar seu carrinho e finalizar o pagamento.\n\n" +
                "Se precisar de ajuda ou tiver qualquer dúvida, estamos aqui para te atender.\n\n" +
                "Agradecemos pela preferência!\n\n" +
                "Atenciosamente,\n" +
                "Acabou mony");
        mailSender.send(message);
        System.out.println("E-mail enviado com sucesso para " + dto.login());
    }
}

