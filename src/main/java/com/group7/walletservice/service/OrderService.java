package com.group7.walletservice.service;

import com.group7.walletservice.dto.OrderRequest;
import com.group7.walletservice.dto.OrderResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Flux<OrderResponse> getAll();
    Mono<OrderResponse> save(OrderRequest orderRequest);

    Mono<OrderResponse> take(String id, String walletSeller);

    Mono<OrderResponse> done(String id, String transactionNumber);
}

