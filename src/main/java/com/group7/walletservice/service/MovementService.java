package com.group7.walletservice.service;

import com.group7.walletservice.dto.MovementRequest;
import com.group7.walletservice.dto.MovementResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {
    Flux<MovementResponse> getAllByWallet(String wallet);
    Mono<MovementResponse> save(MovementRequest walletMovementRequest);
}
