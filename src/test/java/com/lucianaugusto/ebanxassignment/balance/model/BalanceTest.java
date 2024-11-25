package com.lucianaugusto.ebanxassignment.balance.model;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BalanceTest extends BaseTest {

    @Test
    void deposit() {
        Account account = new Account("777");
        Balance balance = new Balance(account, 10);
        Assertions.assertEquals(10, balance.getAmount());

        balance.deposit(10);

        Assertions.assertEquals(20, balance.getAmount());
    }

    @Test
    void withdraw() {
        Account account = new Account("777");
        Balance balance = new Balance(account, 10);

        balance.withdraw(10);

        Assertions.assertEquals(0, balance.getAmount());
    }

    @Test
    void getAccountNumber() {
        String accountNumber = "777";
        Account account = new Account(accountNumber);
        Balance balance = new Balance(account, 10);

        String accNumber = balance.getAccountNumber();

        Assertions.assertEquals(accountNumber, accNumber);
    }

    @Test
    void sendTransfer() {
        String accountNumber = "777";
        Account account = new Account(accountNumber);
        Balance balance = new Balance(account, 10);

        balance.sendTransfer(10);

        Assertions.assertEquals(0, balance.getAmount());
    }

    @Test
    void receiveTransfer() {
        String accountNumber = "777";
        Account account = new Account(accountNumber);
        Balance balance = new Balance(account, 0);

        balance.receiveTransfer(10);

        Assertions.assertEquals(10, balance.getAmount());
    }
}