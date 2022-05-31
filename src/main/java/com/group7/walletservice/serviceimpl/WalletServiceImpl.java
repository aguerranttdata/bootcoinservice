package com.group7.walletservice.serviceimpl;

import com.group7.walletservice.dto.WalletRequest;
import com.group7.walletservice.dto.WalletResponse;
import com.group7.walletservice.exception.wallet.WalletCreationException;
import com.group7.walletservice.exception.wallet.WalletNotFoundException;
import com.group7.walletservice.repository.WalletRepository;
import com.group7.walletservice.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private StreamBridge streamBridge;


    @Override
    public Flux<WalletResponse> getAll() {
        return walletRepository.findAll().map(WalletResponse::fromModel);
    }

    @Override
    public Mono<WalletResponse> save(WalletRequest walletRequest) {
        return Mono.just(walletRequest)
                .flatMap(walletRequest1 -> walletRepository.existsByPhone(walletRequest1.getPhone())
                        .flatMap(existing -> existing ?
                                Mono.error(new WalletCreationException("Phone number exists "+walletRequest1.getPhone())):
                                Mono.just(walletRequest1))
                        )
                .map(WalletRequest::toModel)
                .flatMap(wallet -> walletRepository.save(wallet))
                .map(WalletResponse::fromModel)
                .onErrorMap(ex -> new WalletCreationException(ex.getMessage()))
                .doOnError(ex -> log.error("Error creating new Account ", ex));
    }
}
