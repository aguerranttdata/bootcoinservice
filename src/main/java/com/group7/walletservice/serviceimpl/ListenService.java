package com.group7.walletservice.serviceimpl;

import com.group7.walletservice.dto.MessageKafka;
import com.group7.walletservice.dto.MovementRequest;
import com.group7.walletservice.repository.OrderRepository;
import com.group7.walletservice.repository.WalletRepository;
import com.group7.walletservice.service.MovementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Service
@Slf4j
public class ListenService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private MovementService movementService;

    @Bean
    Consumer<MessageKafka> proccessyanki() {
        return messageKafka -> {
            if(messageKafka.getType().equalsIgnoreCase("response")) {
                if(messageKafka.getDocument().equalsIgnoreCase("account")) {
                    walletRepository.findByPhone(messageKafka.getNumber())
                            .flatMap(wallet -> {
                                wallet.setCanSell(messageKafka.getSuccess());
                                return walletRepository.save(wallet);
                            })
                            .subscribe();
                }
                else {
                    orderRepository.findByTransactionNumber(messageKafka.getMessage())
                            .flatMap(order -> {
                                order.setState("confirm");
                                order.setUpdatedAt(LocalDateTime.now());
                                return orderRepository.save(order)
                                        .then(movementService.save(MovementRequest.builder()
                                                .wallet(order.getWallet())
                                                .type("deposit")
                                                .description("Exchange")
                                                .amount(order.getAmountBootcoin())
                                                .build()))
                                        .then(movementService.save(MovementRequest.builder()
                                                .wallet(order.getWalletSeller())
                                                .type("withdraw")
                                                .description("Exchange")
                                                .amount(order.getAmountBootcoin())
                                                .build()));
                            }).subscribe();
                }
            }
        };
    }

    @Bean
    Consumer<MessageKafka> proccessaccount() {
        return messageKafka -> {
            if(messageKafka.getType().equalsIgnoreCase("response")) {
                if(messageKafka.getDocument().equalsIgnoreCase("account")) {
                    walletRepository.findByAccountNumber(messageKafka.getNumber())
                            .flatMap(wallet -> {
                                wallet.setCanSell(messageKafka.getSuccess());
                                return walletRepository.save(wallet);
                            })
                            .subscribe();
                }
                else {
                    orderRepository.findByTransactionNumber(messageKafka.getMessage())
                            .flatMap(order -> {
                                    log.info("aaaaa");
                                    log.info("messageKafka.getMessage()"+messageKafka.getMessage());
                                    order.setState("confirm");
                                    order.setUpdatedAt(LocalDateTime.now());
                                    return orderRepository.save(order)
                                            .then(movementService.save(MovementRequest.builder()
                                                    .wallet(order.getWallet())
                                                    .type("deposit")
                                                    .description("Exchange")
                                                    .amount(order.getAmountBootcoin())
                                                    .build()))
                                            .then(movementService.save(MovementRequest.builder()
                                                    .wallet(order.getWallet())
                                                    .type("withdraw")
                                                    .description("Exchange")
                                                    .amount(order.getAmountBootcoin())
                                                    .build()));
                            }).subscribe();
                }
            }
        };
    }

}
