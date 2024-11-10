package com.lucianaugusto.ebanxassignment.balance.model;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.base.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class Balance extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private BigDecimal amount;

    Balance() {};

    public Balance(Account account) {
        this.account = account;
    }

    public Balance(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void deposit(BigDecimal amount) {
        this.amount = getAmount().add(amount);
    }
}
