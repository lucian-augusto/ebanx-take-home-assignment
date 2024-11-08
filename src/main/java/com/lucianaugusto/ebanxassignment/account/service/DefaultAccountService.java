package com.lucianaugusto.ebanxassignment.account.service;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public DefaultAccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account createAccount(String accountNumber) {
        return new Account(accountNumber);
    }

    public Optional<Account> findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }
}
