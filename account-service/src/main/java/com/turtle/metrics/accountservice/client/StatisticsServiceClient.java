package com.turtle.metrics.accountservice.client;

import com.turtle.metrics.accountservice.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistics-service", fallback = StatisticsServiceClientFallback.class)
public interface StatisticsServiceClient {
    @PutMapping("/statistics/{name}")
    void updateStatistics(@PathVariable String name, @RequestBody Account account);
}
