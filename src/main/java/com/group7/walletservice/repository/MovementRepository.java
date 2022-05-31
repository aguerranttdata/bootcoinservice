package com.group7.walletservice.repository;

import com.group7.walletservice.dto.MovementResponse;
import com.group7.walletservice.model.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {

    Flux<Movement> findByWallet(String wallet);
}
