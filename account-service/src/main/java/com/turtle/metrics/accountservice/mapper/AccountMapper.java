package com.turtle.metrics.accountservice.mapper;

import com.turtle.metrics.accountservice.dto.AccountResponse;
import com.turtle.metrics.accountservice.dto.ItemDTO;
import com.turtle.metrics.accountservice.dto.SavingDTO;
import com.turtle.metrics.accountservice.model.Account;
import com.turtle.metrics.accountservice.model.Item;
import com.turtle.metrics.accountservice.model.Saving;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    SavingDTO toSavingDTO(Saving saving);
    ItemDTO toItemDTO(Item item);
    AccountResponse toAccountResponse(Account account);
}
