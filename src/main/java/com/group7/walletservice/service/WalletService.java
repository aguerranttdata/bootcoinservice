package com.group7.walletservice.service;

import com.group7.walletservice.dto.PaymentMethodRequest;
import com.group7.walletservice.dto.WalletRequest;
import com.group7.walletservice.dto.WalletResponse;
import com.group7.walletservice.util.MessageResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletService {

    Flux<WalletResponse> getAll();
    Mono<WalletResponse> save(WalletRequest walletRequest);

    Mono<MessageResponse> checkPaymentMethod(PaymentMethodRequest paymentMethodRequest);
}
