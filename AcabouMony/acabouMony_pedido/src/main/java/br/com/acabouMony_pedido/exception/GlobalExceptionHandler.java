package br.com.acabouMony_pedido.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoExiste(UsuarioNaoEncontradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
    @ExceptionHandler(CartaoNaoEncontrado.class)
    public ResponseEntity<String> handleCartaoNaoExiste(CartaoNaoEncontrado e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(ProdutoNaoEncontrado.class)
    public ResponseEntity<String> handleProdutoNaoExiste(ProdutoNaoEncontrado e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<String> handleEstoqueInsuficiente(EstoqueInsuficienteException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(TransacaoRecusadaTipo.class)
    public ResponseEntity<String> handleCartaoPedidoTipoConflitos(TransacaoRecusadaTipo e){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }


    @ExceptionHandler(CreditoInsuficienteException.class)
    public ResponseEntity<String> handleCreditoInsuficiente(CreditoInsuficienteException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }


    @ExceptionHandler(SaldoInsuficienteExcepetion.class)
    public ResponseEntity<String> handleSaldoInsuficiente(SaldoInsuficienteExcepetion e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }




}
