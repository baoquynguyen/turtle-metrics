package com.turtle.metrics.accountservice.service;

import com.turtle.metrics.accountservice.model.Account;
import com.turtle.metrics.accountservice.model.User;

public interface AccountService {

    Account findByName(String name);

    Account create(User user);

    void saveChanges(String name, Account update);
}
