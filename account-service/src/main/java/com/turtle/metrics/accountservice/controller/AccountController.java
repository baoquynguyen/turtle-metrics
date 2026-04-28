package com.turtle.metrics.accountservice.controller;

import com.turtle.metrics.accountservice.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{name}")
    public Account getAccount(@PathVariable String name) {
        return new Account(name, BigDecimal.valueOf(1000));
    }
}
