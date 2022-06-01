package com.group7.walletservice.dto;

import com.group7.walletservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String id;

    private String wallet;
    private String walletSeller;
    private Double amount;
    private Double exchangeRate;
    private Double amountBootcoin;
    private String paymentMethod;
    private String state;
    private LocalDateTime createdAt;

    public static OrderResponse fromModel(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .wallet(order.getWallet())
                .walletSeller(order.getWalletSeller())
                .amount(order.getAmount())
                .paymentMethod(order.getPaymentMethod())
                .exchangeRate(order.getExchangeRate())
                .amountBootcoin(order.getAmountBootcoin())
                .state(order.getState())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
