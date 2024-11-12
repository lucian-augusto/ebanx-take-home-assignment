package com.lucianaugusto.ebanxassignment.account.service;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.repository.AccountRepository;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DefaultAccountServiceTest extends BaseTest {

    @InjectMocks
    private DefaultAccountService service;

    @Mock
    private AccountRepository repository;

    @Test
    void createAccount() {
        String accountNumber = "777";
        Account account = new Account(accountNumber);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        Mockito.when(repository.save(captor.capture())).thenReturn(account);
        Account newAccount = service.createAccount(accountNumber);

        Assertions.assertEquals(account, newAccount);
        Assertions.assertEquals(accountNumber, captor.getValue().getAccountNumber());
    }

    @Test
    void findByAccountNumber() {
        String accountNumber = "777";
        Account account = new Account(accountNumber);

        Mockito.when(repository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        Optional<Account> responseOptional = service.findByAccountNumber(accountNumber);

        Assertions.assertEquals(account, responseOptional.get());
    }
}