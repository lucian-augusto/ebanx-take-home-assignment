package com.lucianaugusto.ebanxassignment.event.operation;

import com.lucianaugusto.ebanxassignment.balance.model.Balance;

public record BalanceInfo(String accountNumber, Integer amount) {

    public BalanceInfo(Balance balance) {
        this(balance.getAccountNumber(), balance.getAmount());
    }
}
