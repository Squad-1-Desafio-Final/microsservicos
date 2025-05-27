package br.com.acabouMony_usuario.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UsuarioConsumer {

    @KafkaListener(topics = "usuario-criado", groupId = "usuario-group")
    public void processarVenda(String mensagem){
        System.out.println(mensagem);
    }

}
