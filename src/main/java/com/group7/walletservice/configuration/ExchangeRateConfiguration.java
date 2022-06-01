package com.group7.walletservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("exchange-rate")
@Getter
@Setter
public class ExchangeRateConfiguration {
    private Double purchasePrice;
    private Double sellPrice;
}
