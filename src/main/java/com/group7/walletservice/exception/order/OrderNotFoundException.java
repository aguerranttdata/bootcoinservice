package com.group7.walletservice.exception.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) { super(message); }
}
