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
@Document(collection = "walletMovements")
public class Movement {
    @Id
    private String id;
    private String wallet;
    private String debitCard;
    private String type;
    private String description;
    private Double amount;
    private LocalDateTime createdAt;
}
