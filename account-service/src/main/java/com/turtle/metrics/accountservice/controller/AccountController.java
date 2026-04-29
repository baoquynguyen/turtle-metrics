package com.turtle.metrics.accountservice.controller;

import com.turtle.metrics.accountservice.model.Account;
import com.turtle.metrics.accountservice.model.User;
import com.turtle.metrics.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{name}")
    public ResponseEntity<Account> getAccountByName(@PathVariable String name) {
        return ResponseEntity.ok(accountService.findByName(name));
    }

    @GetMapping("/current")
    public ResponseEntity<Account> getCurrentAccount(Principal principal) {
        return ResponseEntity.ok(accountService.findByName(principal.getName()));
    }

    @PutMapping("/current")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveCurrentAccount(Principal principal, @RequestBody Account account) {
        accountService.saveChanges(principal.getName(), account);
    }

    @PostMapping
    public ResponseEntity<Account> createNewAccount(@RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountService.create(user));
    }
}
