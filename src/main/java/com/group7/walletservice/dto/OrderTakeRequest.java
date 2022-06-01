package com.group7.walletservice.dto;

import javax.validation.constraints.NotBlank;

public class OrderTakeRequest {
    @NotBlank
    private String wallet;
}
