package com.lucianaugusto.ebanxassignment.event.operation;

import com.lucianaugusto.ebanxassignment.balance.model.Balance;

import java.math.BigDecimal;

public record BalanceInfo(String accountNumber, BigDecimal amount) {

    public BalanceInfo(Balance balance) {
        this(balance.getAccountNumber(), balance.getAmount());
    }
}
