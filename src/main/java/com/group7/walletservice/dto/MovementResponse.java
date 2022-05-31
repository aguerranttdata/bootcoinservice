package com.group7.walletservice.dto;

import com.group7.walletservice.model.Movement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse {
    private String id;
    private String wallet;
    private String debitCard;
    private String type;
    private Double amount;
    private String description;
    private LocalDateTime createdAt;

    public static MovementResponse fromModel(Movement movement) {
        return MovementResponse.builder()
                .id(movement.getId())
                .wallet(movement.getWallet())
                .type(movement.getType())
                .amount(movement.getAmount())
                .description(movement.getDescription())
                .createdAt(movement.getCreatedAt())
                .build();

    }
}
