package com.lucianaugusto.ebanxassignment.account.service;

import com.lucianaugusto.ebanxassignment.account.model.Account;

import java.util.Optional;

public interface AccountService {
    Account createAccount(String accountNumber);
    Optional<Account> findByAccountNumber(String accountNumber);
}
