package br.com.acabouMony_pedido.exception;

public class EstoqueInsuficienteException extends RuntimeException{
    public EstoqueInsuficienteException(String e){
        super(e);
    }
}