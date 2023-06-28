package com.chat.gateway.authentication;

import com.chat.gateway.models.TokenAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
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

    private final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        logger.debug(path, "Request path: {}");

        List<String> publicPaths = List.of("/users", "/auth/login");

        if (publicPaths.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getCookies().getFirst("token").getValue();
        logger.debug(authHeader, "Authorization Header: {}");

        return validateTokenWithUserService(authHeader)
                .flatMap(valid -> {
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
                })
                .onErrorResume(e -> {
                    // Handle the error
                    logger.error("Failed to validate the token", e);
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                });
    }


    @Override
    public int getOrder() {
        return -100;
    }

    private Mono<Boolean> validateTokenWithUserService(String token) {
        try {
            // Prepare the request
            WebClient webClient = webClientBuilder.baseUrl("http://user-service:6100").build();
            return webClient.post()
                    .uri("/auth/validateToken")
                    .bodyValue(Collections.singletonMap("token", token))
                    .retrieve()
                    .bodyToMono(Boolean.class);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Failed to validate the token", e));
        }
    }

}


