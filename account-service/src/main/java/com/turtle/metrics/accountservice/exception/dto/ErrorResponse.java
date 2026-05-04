package com.turtle.metrics.accountservice.exception.dto;

public record ErrorResponse(
        int status,
        String message
) {}
