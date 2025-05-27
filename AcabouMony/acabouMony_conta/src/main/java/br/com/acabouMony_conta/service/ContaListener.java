package br.com.acabouMony_conta.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ContaListener {

    @KafkaListener(topics = "conta_topico", groupId = "conta_group")
    public void processarVenda(String mensagem){
        System.out.println(mensagem);
    }
}
