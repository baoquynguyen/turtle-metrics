package com.turtle.metrics.accountservice.dto;

import com.turtle.metrics.accountservice.model.ItemType;

import java.math.BigDecimal;

public record ItemDTO(
        Long id,
        String title,
        BigDecimal amount,
        ItemType type
) {
}
