package br.com.acabouMony_transacao.exception;

public class CartaoNaoEncontrado extends RuntimeException{
    public CartaoNaoEncontrado(String e){
        super(e);
    }
}
