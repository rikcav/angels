package com.angels.gateway.utils.exception;

import lombok.Getter;

@Getter
public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message) {
        super(message);
    }

}
