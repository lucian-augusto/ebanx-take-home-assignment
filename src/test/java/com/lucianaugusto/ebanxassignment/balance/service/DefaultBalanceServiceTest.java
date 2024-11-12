package com.lucianaugusto.ebanxassignment.balance.service;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.repository.BalanceRepository;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBalanceServiceTest extends BaseTest {

    private Account account;

    @InjectMocks
    private DefaultBalanceService service;

    @Mock
    private BalanceRepository repository;

    @BeforeEach
    void init() {
        account = new Account("777");
    }

    @Test
    void createBalance() {
        Integer amount = 10;
        Balance expectedBalance = new Balance(account, amount);

        ArgumentCaptor<Balance> captor = ArgumentCaptor.forClass(Balance.class);

        Mockito.when(repository.save(captor.capture())).thenReturn(expectedBalance);
        Balance balance = service.createBalance(account, amount);

        Assertions.assertEquals(expectedBalance, balance);
        Assertions.assertEquals(account, captor.getValue().getAccount());
        Assertions.assertEquals(amount, captor.getValue().getAmount());
    }

    @Test
    void deposit() {
        Integer amount = 10;
        Balance expectedBalance = new Balance(account, 10);
        Balance balance = new Balance(account, 0);

        ArgumentCaptor<Balance> captor = ArgumentCaptor.forClass(Balance.class);

        Mockito.when(repository.save(captor.capture())).thenReturn(expectedBalance);
        Balance balanceAfterDeposit = service.deposit(balance, amount);

        Assertions.assertEquals(amount, balanceAfterDeposit.getAmount());
        Assertions.assertEquals(account, captor.getValue().getAccount());
        Assertions.assertEquals(amount, captor.getValue().getAmount());
    }

    @Test
    void withdraw() {
        Integer amount = 10;
        Balance expectedBalance = new Balance(account, 0);
        Balance balance = new Balance(account, amount);

        ArgumentCaptor<Balance> captor = ArgumentCaptor.forClass(Balance.class);

        Mockito.when(repository.save(captor.capture())).thenReturn(expectedBalance);
        Balance balanceAfterDeposit = service.withdraw(balance, amount);

        Assertions.assertEquals(0, balanceAfterDeposit.getAmount());
        Assertions.assertEquals(account, captor.getValue().getAccount());
        Assertions.assertEquals(0, captor.getValue().getAmount());
    }

    @Test
    void sendTransfer() {
    }
}