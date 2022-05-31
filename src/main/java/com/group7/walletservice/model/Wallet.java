package com.group7.walletservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wallets")
public class Wallet {
    @Id
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

    public boolean validMovement(final String type, final Double amount) {
        return type.equalsIgnoreCase("withdraw") && amount > balance;
    }

    public void makeMovement(final String type, final Double amount) {
        if (type.equalsIgnoreCase("withdraw")) {
            balance -= amount;
        } else if (type.equalsIgnoreCase("deposit")) {
            balance += amount;
        }
    }
}
