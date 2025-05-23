package br.com.acabouMony_pedido.exception;

public class CartaoNaoEncontrado extends RuntimeException{
    public CartaoNaoEncontrado(String e){
        super(e);
    }
}
