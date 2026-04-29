package com.turtle.metrics.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Item {

    private String title;

    private BigDecimal amount;

    private Currency currency;

    private TimePeriod period;
}
