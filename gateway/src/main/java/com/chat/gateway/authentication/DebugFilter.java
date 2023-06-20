package com.chat.gateway.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class DebugFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(DebugFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Request: {}", exchange.getRequest());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1000;  // Make sure this runs first
    }
}
