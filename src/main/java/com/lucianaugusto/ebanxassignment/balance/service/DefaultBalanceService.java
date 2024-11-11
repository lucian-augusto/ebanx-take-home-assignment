package com.lucianaugusto.ebanxassignment.balance.service;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultBalanceService implements BalanceService {

    private final BalanceRepository repository;

    public DefaultBalanceService(BalanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Balance createBalance(Account account, BigDecimal amount) {
        Balance balance = new Balance(account, amount);
        return repository.save(balance);
    }

    @Override
    public Balance deposit(Balance balance, BigDecimal amount) {
        balance.deposit(amount);
        return repository.save(balance);
    }

    @Override
    public Balance withdraw(Balance balance, BigDecimal amount) {
        balance.withdraw(amount);
        return repository.save(balance);
    }

    @Override
    public Balance sendTransfer(Balance balance, BigDecimal amount) {
        balance.sendTransfer(amount);
        return repository.save(balance);
    }
}
