package com.turtle.metrics.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Saving {

    private BigDecimal amount;

    private Currency currency;

    private BigDecimal interest;

    private Boolean deposit;

    private Boolean capitalization;
}
