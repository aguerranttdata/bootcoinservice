package com.group7.walletservice.controller;

import com.group7.walletservice.dto.PaymentMethodRequest;
import com.group7.walletservice.dto.WalletRequest;
import com.group7.walletservice.dto.WalletResponse;
import com.group7.walletservice.exception.MessageResponse;
import com.group7.walletservice.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
@Slf4j
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping
    public Flux<WalletResponse> getAll() {
        return walletService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WalletResponse> save(@Valid @RequestBody WalletRequest walletRequest) {
        return walletService.save(walletRequest);
    }

    @PostMapping("payment-method")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MessageResponse> paymentMethod(@Valid @RequestBody PaymentMethodRequest paymentMethodRequest) {
        return null;
    }

}
