package br.com.api_gateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final WebClient webClient;

    // Inicializando um WebClient para encaminhar as requisições
    public GatewayController() {
        this.webClient = WebClient.builder().build();
    }

    // Interceptando qualquer rota que siga /api/{service}/{path}/... (mas não /api/api)
    @RequestMapping("/{service}/{path:^(?!api).*$}/**")
    public Mono<ResponseEntity<String>> proxy(
            @PathVariable String service, // nome do serviço de destino
            @PathVariable String path, // caminho restante
            @RequestHeader HttpHeaders headers, // cabeçalhos da requisição original
            @RequestParam(required = false) MultiValueMap<String, String> queryParams,
            @RequestBody(required = false) Mono<String> body,
            ServerHttpRequest request
    ) {

        // mapeando a requisição
        String url = switch (service) {
            case "conta" -> "http://localhost:8081";
            case "cartao" -> "http://localhost:8081";
            case "produto" -> "http://localhost:8082";
            case "pedido" -> "http://localhost:8082";
            case "transacao" -> "http://localhost:8083";
            case "endereco" -> "http://localhost:8084";
            case "usuario" -> "http://localhost:8084";
            default -> null;
        };

        if (url == null) return Mono.just(ResponseEntity.status(400).build());

        // Remove /api/{service} do caminho para montar o endpoint de destino
        String fullPath =request.getURI().getRawPath().replace("/api/" + service, "");

        // Encaminha a requisição para o serviço de destino
        return webClient.method(request.getMethod())// mesmo método HTTP (GET, POST, etc.)
                .uri(url + fullPath)    // url completa do serviço
                .headers(httpHeaders -> httpHeaders.addAll(headers)) // copia todos os cabeçalhos
                .body(body == null ? Mono.empty() : body, String.class) // encaminha o corpo da requisição (se houver)
                .retrieve()
                .toEntity(String.class); // retorna a resposta como ResponseEntity<String>
    }
}
