package com.lucianaugusto.ebanxassignment.account.model;

import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.base.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Account extends AbstractEntity {

    @Column(unique = true)
    private String accountNumber;

    @OneToOne(mappedBy = "account")
    private Balance balance;

    Account() {};

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Balance getBalance() {
        return balance;
    }
}
