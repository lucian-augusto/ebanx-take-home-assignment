package com.lucianaugusto.ebanxassignment.account.model;

import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.base.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Account extends AbstractEntity {

    @Column(unique = true)
    private String accountNumber;

    @OneToOne(mappedBy = "account")
    @Cascade(CascadeType.ALL)
    private Balance balance;

    Account() {}

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
