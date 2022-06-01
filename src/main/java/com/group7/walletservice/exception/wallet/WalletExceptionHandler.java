package com.group7.walletservice.exception.wallet;

import com.group7.walletservice.util.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class WalletExceptionHandler {
    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse handleWalletNotFoundException(WalletNotFoundException ex) {
        return MessageResponse.builder().message(ex.getMessage()).build();
    }

    @ExceptionHandler(WalletCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleWalletCreationException(WalletCreationException ex) {
        return MessageResponse.builder().message(ex.getMessage()).build();
    }
}
