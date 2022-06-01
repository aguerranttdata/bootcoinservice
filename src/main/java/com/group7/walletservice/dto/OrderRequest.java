package com.group7.walletservice.dto;

import com.group7.walletservice.configuration.ExchangeRateConfiguration;
import com.group7.walletservice.model.Order;
import com.group7.walletservice.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Slf4j
public class OrderRequest {
    @NotBlank
    private String wallet;
    @Positive
    @NotNull
    private Double amount;

    private Double exchangeRate;

    @NotBlank(message = "paymentMethod cannot be empty")
    @Pattern(regexp = "^(yanki|accountbank)$", message = "The documentType must have a value from: 'yanki' or 'accountbank'")
    private String paymentMethod;

    public Order toModel() {
        return Order.builder()
                .wallet(this.wallet)
                .amount(this.amount)
                .paymentMethod(this.paymentMethod)
                .state("ad")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
