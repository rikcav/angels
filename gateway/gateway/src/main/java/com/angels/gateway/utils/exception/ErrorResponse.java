package com.angels.gateway.utils.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String error;

    private final int code;

    public ErrorResponse(String message, int code) {
        this.error = message;
        this.code = code;
    }
}
