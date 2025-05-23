package br.com.acabouMony_transacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(br.com.acabouMony_transacao.exception.UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoExiste(br.com.acabouMony_transacao.exception.UsuarioNaoEncontradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
    @ExceptionHandler(br.com.acabouMony_transacao.exception.CartaoNaoEncontrado.class)
    public ResponseEntity<String> handleCartaoNaoExiste(br.com.acabouMony_transacao.exception.CartaoNaoEncontrado e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


}
