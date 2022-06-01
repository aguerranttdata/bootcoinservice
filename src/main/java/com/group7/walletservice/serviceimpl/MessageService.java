package com.group7.walletservice.serviceimpl;

import com.group7.walletservice.dto.MessageKafka;
import com.group7.walletservice.dto.PaymentMethodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private StreamBridge streamBridge;

    public boolean linkAccountToYanki(MessageKafka messageKafka) {
            return streamBridge.send("proccessyanki-out-0", messageKafka);
    }

    public boolean linkAccountToAccount(MessageKafka messageKafka) {
        return streamBridge.send("proccessaccount-out-0", messageKafka);

    }
}
