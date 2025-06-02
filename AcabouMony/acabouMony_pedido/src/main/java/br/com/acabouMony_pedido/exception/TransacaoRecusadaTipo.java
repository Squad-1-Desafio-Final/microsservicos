package br.com.acabouMony_pedido.exception;

public class TransacaoRecusadaTipo extends RuntimeException {
    public TransacaoRecusadaTipo(String message) {
        super(message);
    }
}