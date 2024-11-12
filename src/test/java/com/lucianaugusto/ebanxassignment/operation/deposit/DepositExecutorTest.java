package com.lucianaugusto.ebanxassignment.operation.deposit;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.service.BalanceService;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import com.lucianaugusto.ebanxassignment.operation.base.enums.OperationTypeEnum;
import com.lucianaugusto.ebanxassignment.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.operation.base.result.OperationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DepositExecutorTest extends BaseTest {

    @InjectMocks
    private DepositExecutor executor;

    @Mock
    private AccountService accountService;

    @Mock
    private BalanceService balanceService;

    @Test
    void getOperationType() {
        assertEquals(OperationTypeEnum.DEPOSIT, executor.getOperationType());
    }

    @Test
    void executeExistingAccount() throws NoSuchFieldException, IllegalAccessException {
        Integer amount = 10;
        String accountNumber = "777";
        Account account = new Account(accountNumber);
        Balance balance = new Balance(account, 0);
        Balance expectedBalance = new Balance(account, amount);
        Field accountBalanceField = account.getClass().getDeclaredField("balance");
        accountBalanceField.setAccessible(true);
        accountBalanceField.set(account, balance);
        accountBalanceField.setAccessible(false);

        OperationRequest request = new OperationRequest(OperationTypeEnum.DEPOSIT, amount);
        request.setDestinationAccountNumber(accountNumber);

        Mockito.when(accountService.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        Mockito.when(balanceService.deposit(balance, amount)).thenReturn(expectedBalance);

        OperationResult result = executor.execute(request);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNull(result.origin());
        Assertions.assertEquals(accountNumber, result.destination().accountNumber());
        Assertions.assertEquals(amount, result.destination().amount());
    }

    @Test
    void executeNewAccount() throws NoSuchFieldException, IllegalAccessException {
        Integer amount = 10;
        String accountNumber = "777";
        Account account = new Account(accountNumber);
        Balance expectedBalance = new Balance(account, amount);

        OperationRequest request = new OperationRequest(OperationTypeEnum.DEPOSIT, amount);
        request.setDestinationAccountNumber(accountNumber);

        Mockito.when(accountService.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());
        Mockito.when(accountService.createAccount(accountNumber)).thenReturn(account);
        Mockito.when(balanceService.createBalance(account, amount)).thenReturn(expectedBalance);

        OperationResult result = executor.execute(request);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNull(result.origin());
        Assertions.assertEquals(accountNumber, result.destination().accountNumber());
        Assertions.assertEquals(amount, result.destination().amount());
    }
}