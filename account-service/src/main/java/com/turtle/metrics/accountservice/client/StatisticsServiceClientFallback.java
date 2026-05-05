package com.turtle.metrics.accountservice.client;

import com.turtle.metrics.accountservice.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatisticsServiceClientFallback implements StatisticsServiceClient{
    @Override
    public void updateStatistics(String name, Account account) {
        log.warn("Statistics service unavailable. Skipping statistics update for account {}", name);
    }
}
