package br.com.api_gateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class GatewayController {

    private final WebClient webClient;

    public GatewayController() {
        this.webClient = WebClient.builder().build();
    }

    // Mapeia qualquer rota que comece com /api/{service}, inclusive só /api/{service}
    @RequestMapping("/{service}/{path:^(?!api).*$}/**")
    public Mono<ResponseEntity<String>> proxy(
            @PathVariable String service,
            @RequestHeader HttpHeaders headers,
            @RequestParam(required = false) MultiValueMap<String, String> queryParams,
            @RequestBody(required = false) Mono<String> body,
            ServerHttpRequest request
    ) {
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

        if (url == null) {
            return Mono.just(ResponseEntity.status(400).build());
        }

        System.out.println(url);

        // Remove "/api/{service}" do path para formar a URL destino
        String fullPath = request.getURI().getRawPath().replace("/api/" + service, "");

        System.out.println("http://localhost:8081/conta");
        return webClient.method(request.getMethod())
                .uri("http://localhost:8081/conta")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(body == null ? Mono.empty() : body, String.class)
                .retrieve()
                .toEntity(String.class);
    }
}