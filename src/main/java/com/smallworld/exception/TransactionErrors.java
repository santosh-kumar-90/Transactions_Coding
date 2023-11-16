package com.smallworld.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionErrors {
    TRANSACTION_FILE_NOT_LOADED("Transaction File could not be loaded!");

    private final String message;
    public CustomException asException() {
        return new CustomException(this.getMessage());
    }
}
