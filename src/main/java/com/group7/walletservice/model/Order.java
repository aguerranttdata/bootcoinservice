package com.group7.walletservice.model;

import com.group7.walletservice.configuration.ExchangeRateConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Order {

    @Id
    private String id;

    private String wallet;
    private String walletSeller;
    private Double amount;

    private String destinyNumber;
    private String transactionNumber;

    private Double exchangeRate;
    private Double amountBootcoin;
    private String paymentMethod;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void calculateAmountBootcoin(Double amount) {
        Double exchangeRate = 3.763;
        log.info("this.exchangeRate2", exchangeRate);
        this.exchangeRate = exchangeRate;
        BigDecimal db = new BigDecimal(amount/exchangeRate).setScale(2, RoundingMode.HALF_UP);
        this.amountBootcoin = db.doubleValue();
    }

}
