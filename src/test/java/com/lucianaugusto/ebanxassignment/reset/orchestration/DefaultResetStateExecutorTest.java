package com.lucianaugusto.ebanxassignment.reset.orchestration;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.repository.AccountRepository;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.repository.BalanceRepository;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class DefaultResetStateExecutorTest extends BaseTest {

    @InjectMocks
    private DefaultResetStateExecutor executor;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @Test
    void resetStateSuccess() {
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        ArgumentCaptor<Balance> balanceArgumentCaptor = ArgumentCaptor.forClass(Balance.class);

        Account account = new Account("300");
        Balance balance = new Balance(account, 0);
        Mockito.when(accountRepository.save(accountArgumentCaptor.capture())).thenReturn(account);
        Mockito.when(balanceRepository.save(balanceArgumentCaptor.capture())).thenReturn(balance);

        boolean success = executor.resetState();

        Assertions.assertTrue(success);
        Assertions.assertEquals("300", accountArgumentCaptor.getValue().getAccountNumber());
        Assertions.assertEquals(accountArgumentCaptor.getValue(), balanceArgumentCaptor.getValue().getAccount());
        Assertions.assertEquals(0, balanceArgumentCaptor.getValue().getAmount());
        Mockito.verify(accountRepository).deleteAll();
        Mockito.verify(balanceRepository).deleteAll();
    }

    @Test
    void resetStateWithException() {
        Mockito.doThrow(RuntimeException.class).when(accountRepository).deleteAll();
        boolean success = executor.resetState();

        Assertions.assertFalse(success);
        Mockito.verify(accountRepository).deleteAll();
        Mockito.verify(balanceRepository).deleteAll();
    }
}