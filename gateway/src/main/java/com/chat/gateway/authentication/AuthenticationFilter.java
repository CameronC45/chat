package com.chat.gateway.authentication;

import com.chat.gateway.models.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        System.out.println(path);


        List<String> publicPaths = List.of("/users", "/auth/login");

        if (publicPaths.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        // Get the Authorization header
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // Validate the token with User Service
        // This assumes UserService has an endpoint /validateToken that verifies the token
        boolean valid = validateTokenWithUserService(authHeader);

        if (valid) {
            // If valid, set the authentication in Security Context
            Authentication authentication = new TokenAuthentication(authHeader);
            authentication.setAuthenticated(true);
            SecurityContextImpl securityContext = new SecurityContextImpl(authentication);
            return chain.filter(exchange.mutate().principal(Mono.just(securityContext.getAuthentication())).build());

        } else {
            // If not valid, return 401 Unauthorized
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -100;  // The order is important, make sure this runs early
    }

    private boolean validateTokenWithUserService(String token) {
        try {
            // Prepare the request
            WebClient webClient = webClientBuilder.baseUrl("http://localhost:6100").build(); // Replace with the actual URL of User Service
            Mono<Boolean> mono = webClient.post()
                    .uri("/auth/validateToken")
                    .bodyValue(Collections.singletonMap("token", token)) // Replace with the actual structure of the request body
                    .retrieve()
                    .bodyToMono(Boolean.class);  // Replace with the actual structure of the response body
            return mono.block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate the token", e);
        }
    }
}

