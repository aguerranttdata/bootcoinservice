package com.group7.walletservice.exception.wallet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Wallet creation exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WalletCreationException extends RuntimeException {
    public WalletCreationException(String message) { super(message); }


}
