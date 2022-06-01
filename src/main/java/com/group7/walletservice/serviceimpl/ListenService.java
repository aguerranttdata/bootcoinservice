package com.group7.walletservice.serviceimpl;

import com.group7.walletservice.dto.MessageKafka;
import com.group7.walletservice.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Service
@Slf4j
public class ListenService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private StreamBridge streamBridge;

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
            }
        };
    }

}
