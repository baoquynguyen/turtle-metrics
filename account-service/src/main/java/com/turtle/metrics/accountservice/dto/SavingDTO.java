package com.turtle.metrics.accountservice.dto;

import java.math.BigDecimal;

public record SavingDTO(
        BigDecimal amount,
        BigDecimal interest,
        Boolean deposit
) {
}
