package com.lucianaugusto.ebanxassignment.balance.model;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.base.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Optional;

@Entity
public class Balance extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Integer amount;

    Balance() {}

    public Balance(Account account, Integer amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return Optional.of(getAccount().getAccountNumber()).orElse(null);
    }

    public void deposit(Integer amount) {
        addAmount(amount);
    }

    public void withdraw(Integer amount) {
        subtractAmount(amount);
    }

    public void sendTransfer(Integer amount) {
        subtractAmount(amount);
    }

    public void receiveTransfer(Integer amount) {
        addAmount(amount);
    }

    private void addAmount(Integer amount) {
        this.amount += amount;
    }

    private void subtractAmount(Integer amount) {
        this.amount -= amount;
    }
}
