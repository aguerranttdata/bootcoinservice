package com.group7.walletservice.dto;

import com.group7.walletservice.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class WalletRequest {
    @NotBlank(message = "firstName cannot be empty")
    private String firstName;

    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @NotBlank(message = "documentType cannot be empty")
    @Pattern(regexp = "^(DNI|CE|Pasaporte)$", message = "The documentType must have a value from: 'DNI' or 'CE' or 'Pasaporte'")
    private String documentType;

    @NotBlank(message = "documentNumber cannot be empty")
    @Size(max=15, min = 8, message = "documentNumber min length is 8 and max length is 15")
    private String documentNumber;

    @NotBlank(message = "phone cannot be empty")
    private String phone;

    @Email
    private String email;

    public Wallet toModel() {
        return Wallet.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .documentType(this.documentType)
                .documentNumber(this.documentNumber)
                .phone(this.phone)
                .balance(0.0)
                .email(this.email)
                .canSell(false)
                .active(true)
                .build();
    }
}
