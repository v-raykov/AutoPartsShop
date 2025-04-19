package com.viktor.oop.car.parts.shop.config.exception.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final HttpStatus status;
    private final String message;
}
