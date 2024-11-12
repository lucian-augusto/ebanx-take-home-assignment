package com.lucianaugusto.ebanxassignment.reset.orchestration;

import com.lucianaugusto.ebanxassignment.account.repository.AccountRepository;
import com.lucianaugusto.ebanxassignment.balance.repository.BalanceRepository;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        boolean success = executor.resetState();

        Assertions.assertTrue(success);
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