package br.com.acabouMony_pedido.exception;

public class CreditoInsuficienteException extends RuntimeException{
    public CreditoInsuficienteException(String e){
        super(e);
    }
}
