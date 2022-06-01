package com.group7.walletservice.serviceimpl;

import com.group7.walletservice.dto.PaymentMethodRequest;
import com.group7.walletservice.dto.WalletRequest;
import com.group7.walletservice.dto.WalletResponse;
import com.group7.walletservice.exception.wallet.WalletCreationException;
import com.group7.walletservice.exception.wallet.WalletNotFoundException;
import com.group7.walletservice.repository.WalletRepository;
import com.group7.walletservice.service.WalletService;
import com.group7.walletservice.util.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private MessageService messageService;

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

    @Override
    public Mono<MessageResponse> checkPaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        return Mono.just(paymentMethodRequest)
                .map(PaymentMethodRequest::toMessageKafka)
                .flatMap(messageKafka -> walletRepository.findById(paymentMethodRequest.getWallet())
                        .switchIfEmpty(Mono.error(new WalletNotFoundException("Wallet not found with id: "+paymentMethodRequest.getWallet())))
                        .flatMap(existing -> {

                            if(paymentMethodRequest.getAccountType().equalsIgnoreCase("yanki")) {
                                messageKafka.setNumber(existing.getPhone());
                            }
                            else {
                                if(Objects.isNull(paymentMethodRequest.getAccountNumber())) {
                                    return Mono.error(new WalletCreationException("field accountNumber is required"));
                                }
                            }

                            existing.setAccountType(paymentMethodRequest.getAccountType());
                            existing.setAccountNumber(paymentMethodRequest.getAccountNumber());
                            existing.setCanSell(false);

                            return walletRepository.save(existing)
                                    .then(Mono.just(new MessageResponse("Registered Successfully")));
                        }).doOnSuccess(res -> {
                            if (paymentMethodRequest.getAccountType().equalsIgnoreCase("yanki")) {
                                messageService.linkAccountToYanki(messageKafka);
                            }
                            else {
                                messageService.linkAccountToAccount(messageKafka);
                            }
                        })
                )
                .onErrorMap(ex -> new WalletCreationException(ex.getMessage()))
                .doOnError(ex -> log.error("Error creating new Account ", ex));
    }
}
