package com.group7.walletservice.dto;

import com.group7.walletservice.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String phone;
    private Double balance;
    private String email;
    private String accountType;
    private String accountNumber;
    private Boolean canSell;
    private Boolean active;
    private LocalDateTime createdAt;

    public static WalletResponse fromModel(Wallet wallet) {
        return WalletResponse.builder()
                .id(wallet.getId())
                .firstName(wallet.getFirstName())
                .lastName(wallet.getLastName())
                .documentType(wallet.getDocumentType())
                .documentNumber(wallet.getDocumentNumber())
                .phone(wallet.getPhone())
                .balance(wallet.getBalance())
                .email(wallet.getEmail())
                .accountType(wallet.getAccountType())
                .accountNumber(wallet.getAccountNumber())
                .canSell(wallet.getCanSell())
                .active(wallet.getActive())
                .createdAt(wallet.getCreatedAt())
                .build();
    }
}
