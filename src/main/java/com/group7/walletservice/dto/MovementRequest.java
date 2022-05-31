package com.group7.walletservice.dto;

import com.group7.walletservice.model.Movement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovementRequest {
    @NotBlank(message = "phone cannot be empty")
    private String wallet;

    @NotBlank(message = "type cannot be empty")
    private String type;

    private String description;
    @NotNull(message = "amount cannot be null")
    private Double amount;

    public Movement toModel() {
        return Movement.builder()
                .wallet(this.wallet)
                .type(this.type)
                .amount(this.amount)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
