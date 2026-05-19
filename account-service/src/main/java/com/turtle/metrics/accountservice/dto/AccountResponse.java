package com.turtle.metrics.accountservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AccountResponse(
        Long id,
        String name,
        LocalDateTime lastSeen,
        SavingDTO saving,
        String note,
        List<ItemDTO> items
) {
}
