package br.com.api_gateway.filter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Base64;

@Component
public class AuthWebFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthWebFilter.class);

    private final Key secretKey; // chave secreta para validar o JWT

    // Injeta a chave em Base64 do application.properties ou variável de ambiente
    public AuthWebFilter(@Value("${api.security.token.secret}") String secretKeyString) {
        try {
            this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
        } catch (Exception e) {
            logger.error("Erro ao criar a chave JWT: {}", e.getMessage());
            throw new IllegalArgumentException("Chave secreta inválida. Verifique a configuração em api.security.token.secret", e);
        }
    }

    // Método principal chamado em cada requisição
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Obtém o cabeçalho Authorization
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // Se o cabeçalho não existir ou não começar com "Bearer ", retorna 401 Unauthorized

        if (authHeader == null) {
            logger.warn("Cabeçalho Authorization ausente ou inválido: {}", authHeader);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(0);
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            logger.debug("Token JWT válido para a requisição: {}", exchange.getRequest().getURI());

            return chain.filter(exchange);
        } catch (JwtException e) {
            logger.warn("Falha na validação do token JWT: ", e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return exchange.getResponse().setComplete();
        }
    }
}