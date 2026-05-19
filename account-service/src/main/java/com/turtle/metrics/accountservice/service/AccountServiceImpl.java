package com.turtle.metrics.accountservice.service;

import com.turtle.metrics.accountservice.client.StatisticsServiceClient;
import com.turtle.metrics.accountservice.dto.AccountResponse;
import com.turtle.metrics.accountservice.exception.AccountAlreadyExistsException;
import com.turtle.metrics.accountservice.exception.AccountNotFoundException;
import com.turtle.metrics.accountservice.mapper.AccountMapper;
import com.turtle.metrics.accountservice.model.*;
import com.turtle.metrics.accountservice.repository.AccountRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final StatisticsServiceClient statisticsServiceClient;

    @Override
    public AccountResponse findByName(String name) {
        Assert.hasLength(name, "Name must not be empty");
        Account account = accountRepository.findByName(name)
                .orElseThrow(() -> new AccountNotFoundException(name));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse create(User user) {
        Assert.notNull(user, "User must not be null");
        Assert.hasText(user.getUsername(), "Username must not be empty");

        accountRepository.findByName(user.getUsername())
                .ifPresent(account -> {
                    throw new AccountAlreadyExistsException(user.getUsername());
                });

        //authClient.createUser(user);

        Account account = buildNewAccount(user.getUsername());
        accountRepository.save(account);

        log.info("Created account {}", account);
        return accountMapper.toAccountResponse(account);
    }

    @Override
    @Transactional
    public void saveChanges(String name, Account update) {
        Assert.notNull(update, "updated account must not be null");
        Assert.hasText(name, "name must not be empty");

        Account account = accountRepository.findByName(name)
                .orElseThrow(() -> new AccountNotFoundException(name));

        applyChanges(account, update);

        accountRepository.save(account);

        log.info("Account {} changes have been updated", name);

        updateStatisticsSafely(name, account);
    }

    private Account buildNewAccount(String username) {
        Saving saving = new Saving();
        saving.setAmount(BigDecimal.ZERO);
        saving.setInterest(BigDecimal.ZERO);
        saving.setDeposit(false);

        Account account = new Account();
        account.setName(username);
        account.setLastSeen(LocalDateTime.now());
        account.setSaving(saving);

        return account;
    }

    private void applyChanges(Account target, Account update) {
        target.setItems(update.getIncomes());
        target.setItems(update.getExpense());

        for(Item item : update.getItems()) {
            item.setAccount(target);
        }
        target.setSaving(update.getSaving());
        target.setNote(update.getNote());
        target.setLastSeen(LocalDateTime.now());
    }

    @Retry(name = "statisticsService")
    @CircuitBreaker(name = "statisticsService", fallbackMethod = "statisticsFallback")
    private void updateStatisticsSafely(String name, Account account){
        statisticsServiceClient.updateStatistics(name, account);
    }

    private void statisticsFallback(String name, Account account, Throwable ex) {
        log.error("Statistics update failed for account {}. Will retry later", name, ex);
    }
}
