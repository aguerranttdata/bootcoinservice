package com.group7.walletservice.exception.movement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class MovementCreationException extends RuntimeException {
    public MovementCreationException(String message) {
        super(message);
    }
}
