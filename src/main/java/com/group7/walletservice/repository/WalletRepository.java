package com.group7.walletservice.repository;

import com.group7.walletservice.model.Wallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletRepository extends ReactiveMongoRepository<Wallet, String> {
    Mono<Wallet> findByPhone(String phone);
    Mono<Boolean> existsByPhone(String phone);
}
