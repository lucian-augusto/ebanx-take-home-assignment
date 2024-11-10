package com.lucianaugusto.ebanxassignment.balance.service;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;

import java.math.BigDecimal;

public interface BalanceService {
    Balance createBalance(Account account, BigDecimal amount);
    Balance deposit(Balance balance, BigDecimal amount);
}
