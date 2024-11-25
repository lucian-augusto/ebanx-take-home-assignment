package com.lucianaugusto.ebanxassignment.balance.service;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;

public interface BalanceService {
    Balance createBalance(Account account, Integer amount);
    Balance deposit(Balance balance, Integer amount);
    Balance withdraw(Balance balance, Integer amount);
    Balance sendTransfer(Balance balance, Integer amount);
    Balance receiveTransfer(Balance balance, Integer amount);
}
