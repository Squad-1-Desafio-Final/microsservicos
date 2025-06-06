package br.com.acabouMony_transacao.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public class TransacaoConsumer {
    @KafkaListener(topics = "transacao-feita", groupId = "transacao-group")
    public void processarTransacao(String mensagem){
        System.out.println(mensagem);
    }

}
