package com.lucianaugusto.ebanxassignment.balance.controller;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.Optional;

class BalanceControllerTest extends BaseTest {

    @InjectMocks
    private BalanceController controller;

    @Mock
    private AccountService service;

    @Test
    void getBalanceExistingAccount() throws NoSuchFieldException, IllegalAccessException {
        String accountNumber = "777";
        Account account = new Account(accountNumber);
        Balance balance = new Balance(account, 10);
        Field balanceField = account.getClass().getDeclaredField("balance");
        balanceField.setAccessible(true);
        balanceField.set(account, balance);
        balanceField.setAccessible(false);
        Optional<Account> accountOptional = Optional.of(account);

        Mockito.when(service.findByAccountNumber(accountNumber)).thenReturn(accountOptional);
        ResponseEntity<?> response = controller.getBalance(accountNumber);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(10, response.getBody());
    }

    @Test
    void getBalanceNonExistingAccount() throws NoSuchFieldException, IllegalAccessException {
        String accountNumber = "777";
        Optional<Account> accountOptional = Optional.empty();

        Mockito.when(service.findByAccountNumber(accountNumber)).thenReturn(accountOptional);
        ResponseEntity<?> response = controller.getBalance(accountNumber);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(0, response.getBody());
    }
}