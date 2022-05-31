package com.group7.walletservice.service;

import com.group7.walletservice.dto.WalletRequest;
import com.group7.walletservice.dto.WalletResponse;
import com.group7.walletservice.model.Wallet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletService {

    Flux<WalletResponse> getAll();
    Mono<WalletResponse> save(WalletRequest walletRequest);
}
