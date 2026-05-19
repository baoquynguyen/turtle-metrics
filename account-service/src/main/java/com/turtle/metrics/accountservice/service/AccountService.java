package com.turtle.metrics.accountservice.service;

import com.turtle.metrics.accountservice.dto.AccountResponse;
import com.turtle.metrics.accountservice.model.Account;
import com.turtle.metrics.accountservice.model.User;

public interface AccountService {

    AccountResponse findByName(String name);

    AccountResponse create(User user);

    void saveChanges(String name, Account update);
}
