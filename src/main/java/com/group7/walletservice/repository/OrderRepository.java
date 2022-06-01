package com.group7.walletservice.repository;

import com.group7.walletservice.dto.OrderRequest;
import com.group7.walletservice.dto.OrderResponse;
import com.group7.walletservice.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Mono<Order> findByWallet(String wallet);

    Mono<Order> findByTransactionNumber(String transactionNumber);
}
