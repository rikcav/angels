package com.angels.gateway.utils.exception;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger;

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<?> handleUnprocessableContentException(WebClientResponseException exception) {
        if (exception.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
            return ResponseEntity.status(exception.getStatusCode().value())
                    .body(exception.getResponseBodyAs(Map.class));
        }

        logger.error("Connect error " + Objects.requireNonNull(exception.getRequest()).getURI(), exception);

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_GATEWAY.value()));
    }

    @ExceptionHandler(BadGatewayException.class)
    @CacheEvict(value = "parameters", allEntries = true)
    public ResponseEntity<ErrorResponse> handleBadGatewayException(BadGatewayException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_GATEWAY.value()));
    }
}
